package com.swm.baseframework.app.member.config;

import com.swm.baseframework.app.member.jwt.JwtAccessDeniedHandler;
import com.swm.baseframework.app.member.jwt.JwtAuthenticationEntryPoint;
import com.swm.baseframework.app.member.jwt.JwtSecurityConfig;
import com.swm.baseframework.app.member.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/h2/**",
                        "/favicon.ico",
                        "/error"
                )
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token??? ???????????? ???????????? ????????? csrf??? disable?????????.
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // exception handling ??? ??? ????????? ?????? ???????????? ??????
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // h2-console ??? ?????? ????????? ??????
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // ????????? ???????????? ?????? ????????? STATELESS??? ??????(default: ??????)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // ????????????, ????????? ??? ????????????(jwt) ???????????? API(permitAll)
                .and()
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/reissue").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/signup/admin").permitAll()

                // ???????????? ?????? ?????? ??????(jwt)
                .anyRequest().authenticated()

                // JwtFilter ??? addFilterBefore ??? ???????????? JwtSecurityConfig ???????????? ??????
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}