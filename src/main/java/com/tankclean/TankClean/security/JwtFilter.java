package com.tankclean.TankClean.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

                                //4
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path=request.getRequestURI();

        // ✅ Skip filter for auth APIs
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header=request.getHeader("Authorization");

        if (header !=null && header.startsWith("Bearer ")) {
            try {
                String token=header.substring(7);//Removes "Bearer " (7 characters) // eg->Bearer abc123 → abc123
                String email=jwtUtil.extractEmail(token);//Reads email stored inside JWT

                var userDetails=customUserDetailsService.loadUserByUsername(email);

                if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());          //User is authenticated,Here is user data,Here are roles

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
                return;
            }
        }
        filterChain.doFilter(request,response);

    }
}
