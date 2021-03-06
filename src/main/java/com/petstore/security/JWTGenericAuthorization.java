package com.petstore.security;
/*
 *@author tobi
 * created on 09/05/2020
 *
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import us.ylabs.service.user.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static us.ylabs.security.SecurityConstansts.*;

@Slf4j
@Component
public class JWTGenericAuthorization extends OncePerRequestFilter {

    @Autowired
    @Qualifier("userdetails")
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);


        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
            log.info("The UsernamePasswordAuthenticationToken from JWTAuthorization {}", authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }


    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);
        log.info("HEADER GET {} ", token);
        if (token != null) {
            //parse the token
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null ) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(user);
                log.info("UserDetails for error => {}",userDetails);

                return new UsernamePasswordAuthenticationToken(user, userDetails.getPassword(),
                        userDetails.getAuthorities());
            }
//            return null;
        }
        log.info("TOKEN IS NULL!!!");
        return null;
    }



}
