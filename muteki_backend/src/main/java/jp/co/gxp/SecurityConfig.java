package jp.co.gxp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 参考
    // https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/job/isLogin/*").authenticated()//尽量把需要拦截的放在一样的url，比如job/*
                                .anyRequest().permitAll()
                )
                .csrf().disable()
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                //成功ハンドラー
                                .successHandler((request, response, authentication) -> response.setStatus(HttpStatus.OK.value()))
                                //失敗ハンドラー
                                .failureHandler((request, response, exception) -> response.setStatus(HttpStatus.UNAUTHORIZED.value()))
                ).logout(logout -> logout.logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(HttpStatus.OK.value());
                })).exceptionHandling().authenticationEntryPoint((new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        return http.build();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    // Beanとして登録しておけば自動的にこのEncoderが使われる
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("1234"));
        return bCryptPasswordEncoder;
    }

}