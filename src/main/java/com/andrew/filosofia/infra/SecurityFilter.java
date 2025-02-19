package com.andrew.filosofia.infra;


import com.andrew.filosofia.exception.exceptions.MyTokenExceptionHandler;
import com.andrew.filosofia.user.User;
import com.andrew.filosofia.user.UserRepository;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final MyTokenExceptionHandler myTokenException;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository, MyTokenExceptionHandler myTokenException){
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.myTokenException = myTokenException;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = recoverToken(request);

        if(jwt != null) {
            try {
                String subject = tokenService.validateTokenAndGetUserId(jwt);
                System.out.println(subject);
                User user = userRepository.findById(subject).orElseThrow();

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException exception) {
                System.out.println(exception.getMessage());
                myTokenException.handleTokenException(response);
                return;
            }
        }

        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request) {

        String jwt = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    jwt = cookie.getValue();
                }
            }
        }

        return jwt;
    }

}
