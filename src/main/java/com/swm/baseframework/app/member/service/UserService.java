package com.swm.baseframework.app.member.service;

import com.swm.baseframework.app.member.domain.Authority;
import com.swm.baseframework.app.member.domain.User;
import com.swm.baseframework.app.member.dto.UserDto;
import com.swm.baseframework.app.member.event.UserCreatedEvent;
import com.swm.baseframework.app.member.exception.DuplicateMemberException;
import com.swm.baseframework.app.member.repository.UserRepository;
import com.swm.baseframework.app.member.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public UserDto signup(UserDto userDto) {
        return this.signup(userDto, null);
    }

    @Transactional
    public UserDto signup(UserDto userDto, Authority authority) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        if (authority == null) {
            authority = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();
        }

        User user = userRepository.save(User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build());

        eventPublisher.publishEvent(new UserCreatedEvent(user.getUserId()));

        return UserDto.from(user);
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) {
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }
}