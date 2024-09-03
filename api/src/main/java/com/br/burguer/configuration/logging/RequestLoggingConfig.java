package com.br.burguer.configuration.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class RequestLoggingConfig extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingConfig.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {
        try {
            String method = request.getMethod().toUpperCase();
            String uri = request.getRequestURI();
            logger.info("\"{}\" '{}'", method, uri);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            logger.error("Exception: {}", ex.getMessage());
            throw ex;
        }
    }
}
