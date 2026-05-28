package com.example.Todo.SecurityConfig;

import com.example.Todo.Service.JwtService;
import com.example.Todo.Service.UserDetailServic;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Getter
@Setter
@Component
@AllArgsConstructor
public class Jwtfilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailServic userDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader;
        final String jwt;
        final String username;

        authHeader = request.getHeader("Authorization");
        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        System.out.println("FILTER CALLED");
        System.out.println(authHeader);
        System.out.println(jwt);
        username = jwtService.extractUsername(jwt);
        System.out.println(username);
        if (username != null
                && SecurityContextHolder.getContext()
                .getAuthentication() == null) {
            UserDetails userDetails =
                    userDetailService.loadUserByUsername(username);

            System.out.println("Before validation");
            if (jwtService.isTokenValid(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
            System.out.println(jwtService.isTokenValid(jwt, userDetails));

        }
        filterChain.doFilter(request, response);
    }

}
