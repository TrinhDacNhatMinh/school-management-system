package com.minh.school_management_system.security;

import com.minh.school_management_system.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil; // Utility class for handling JWT token
    private final UserService userService; // Service to load user details from DB

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Retrieve the Authorization header from the HTTP request
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // 2. Check if the header is present and start with "Bearer"
        //    If yes, extract the token part
        if(authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7); // Remove "Bearer " prefix
            try {
                // Extract username from the JWT token
                username = jwtUtil.getUsernameFromToken(token);
            } catch (Exception e) {
                // If the token is invalid or expired, ignore and continue
            }
        }

        // 3. If username is retrieved and authentication is not yet set in context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from DB
            UserDetails userDetails = userService.loadUserByUsername(username);

            // Validate the token
            if (jwtUtil.validateToken(token)) {
                // Create authentication token for Spring Security
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in SecurityContext to mark the user as authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 4. Continue with the filter chain
        filterChain.doFilter(request, response);
    }

}
