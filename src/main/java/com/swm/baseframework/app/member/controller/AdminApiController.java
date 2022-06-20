//package com.swm.baseframework.app.member.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import swm.homework.jobis.dto.UserDto;
//import swm.homework.jobis.entity.Authority;
//import swm.homework.jobis.service.UserService;
//
//import javax.validation.Valid;
//
//@Tag(name = "관리자")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/szs")
//public class AdminApiController {
//
//    private final UserService userService;
//
//    /**
//     * 관리자 생성
//     * @param userDto
//     * @return
//     */
//    @Operation(summary = "어드민 생성", hidden = true)
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @PostMapping("/signup/admin")
//    public ResponseEntity<UserDto> signupForAdmin(
//            @Valid @RequestBody UserDto userDto
//    ) {
//        Authority authority = Authority.createAuthority("ROLE_ADMIN");
//
//        return ResponseEntity.ok(userService.signup(userDto, authority));
//    }
//
//    /**
//     * 회원정보 조회
//     * @param userid
//     * @return
//     */
//    @Operation(summary = "회원정보 조회", hidden = true)
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @GetMapping("/user/{userid}")
//    public ResponseEntity<UserDto> getUserInfo(@PathVariable String userid) {
//        return ResponseEntity.ok(userService.getUserDto(userid));
//    }
//}