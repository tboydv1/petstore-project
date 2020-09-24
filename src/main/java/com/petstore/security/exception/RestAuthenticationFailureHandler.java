package com.petstore.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* Aniefiok
 *created on 5/17/2020
 *inside the package */

@Component
public class RestAuthenticationFailureHandler extends CustomFilterResponse implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                        AuthenticationException ex) throws IOException, ServletException
    {

        new CustomFilterResponse("Unauthorized access",httpServletResponse);

    }
}
