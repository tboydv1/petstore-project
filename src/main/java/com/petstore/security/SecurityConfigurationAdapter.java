package com.petstore.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import us.ylabs.security.exception.RestAccessDeniedHandler;
import us.ylabs.security.exception.RestAuthenticationEntryPoint;
import us.ylabs.security.exception.RestAuthenticationFailureHandler;
import us.ylabs.service.user.UserDetailsServiceImpl;

import java.util.Arrays;

import static us.ylabs.security.SecurityConstansts.SIGN_UP_URL;


@Configuration
@EnableWebSecurity
@Order(1000)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {


/*
    private static final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher(ADMIN_URL+"/**"),
            new AntPathRequestMatcher(INVESTMENT_URL,"POST"),
            new AntPathRequestMatcher(INVESTMENT_URL+"/**","PATCH"),
            new AntPathRequestMatcher(INVESTMENT_URL+"/**","DELETE")
    );
*/

    @Autowired
    JWTGenericAuthorization jwtGenericAuthorization;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    private AuthenticationProvider provider;

    @Value("${client.dev.url}")
    private String clientDevUrl;

    @Value("${client.prod.url}")
    private String clientProdUrl;


//    public static String[] SWAGGER_URL_PATHS = new String[] {
//            "/swagger-ui.html**", "/swagger-resources/**",
//            "/v2/api-docs**", "/webjars/**", "/static/**"
//    };



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().configurationSource(corsConfigurationSource()).and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .addFilterBefore(jwtGenericAuthorization, UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthorizationLoginFilter())
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers("/api/*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN")
                .antMatchers("/admin/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
//                .httpBasic().disable()
                .logout().disable();

//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/perform_login")
//                .successHandler(successHandler())
//                .defaultSuccessUrl("/admin/dashboard", true)
//                //.failureUrl("/login.html?error=true")
//                .failureHandler(authenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID");

        http.requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","PUT","DELETE"));
        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("Authorization");
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Access-Control-Allow-Origin");
        configuration.addExposedHeader("Access-Control-Allow-Headers");
        configuration.addExposedHeader("ETag");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    RestAccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler();
    }

    @Bean
    RestAuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    RestAuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

//    @Bean
//    RestAuthenticationSuccessHandler successHandler() {
//        return new RestAuthenticationSuccessHandler();
//    }

    public JWTAuthenticationFilter jwtAuthorizationLoginFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");
        return jwtAuthenticationFilter;
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("/succeslogin");
        return successHandler;
    }
}
