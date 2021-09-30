package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() // h2-console을 사용하기 위해 해당 옵션 disable
                .and()
                    .authorizeRequests() // url별 권환 관리 설정 옵션의 시작점
                                        // antMatcher: 권한 관리 대상을 지정하는 옵션, url-http 메소드 별 관리 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // anyRequest: 설정된 값들 이외의 나머지 url
                .and()                              // authenticated를 통해 나머지 url은 인증된 사용자에게만 허용
                    .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공시 /로 보내겠다
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
                // oauth2Login: OAuth2 로그인 기능에 대한 설정 진입점
                // userInfoEndpoint: OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                // userService: 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
