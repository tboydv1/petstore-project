package com.petstore.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class RequestFilter extends AbstractRequestLoggingFilter {

    private long time = 0;

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {

        time = System.currentTimeMillis();
//        log.info("REQUEST URI --> " + httpServletRequest.getRequestURI());
//        log.info("REQUEST URL --> " + httpServletRequest.getRequestURL());
//        log.info("REQUEST USER --> " + httpServletRequest.getRemoteUser());
//        log.info("REMOTE HOST --> " + httpServletRequest.getRemoteHost());

    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {
//        log.info("WORKING TIME -->> " + (System.currentTimeMillis() - time));
    }
}
