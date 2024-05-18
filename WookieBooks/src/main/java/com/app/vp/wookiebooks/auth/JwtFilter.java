package com.app.vp.wookiebooks.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Consider of chain responsibility principle;
 * need to:
 * -extends OncePerRequestFilter;
 * -inject JwtService;
 * -inject UserDetailsService;
 *
 * 1)taking token if exist
 * 2)matching data (if token valid we are compare it for authentication)
 * */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //1)taking token
        String headerAuth = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if(headerAuth != null){
            if(headerAuth.startsWith("Bearer ")){
                token = headerAuth.substring(7);
                userName = jwtService.extractUserName(token);
            }
        }
        //2)matching
        if(userName != null){
            UserDetails userDetails = userDetailService.loadUserByUsername(userName);
            //validation
            if(jwtService.validateToken(token, userDetails)){
                //authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("REQUEST IS AUTHENTICATED!");
            }
        }
        filterChain.doFilter(request, response);

    }
}
