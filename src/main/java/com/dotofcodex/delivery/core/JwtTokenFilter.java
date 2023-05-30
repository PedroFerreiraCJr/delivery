package com.dotofcodex.delivery.core;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dotofcodex.delivery.service.CadastroClienteService;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CadastroClienteService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validateToken(token)) {
            chain.doFilter(request, response);
            return;
        }
        
        UserDetails userDetails = service
            .loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token));
        
        UsernamePasswordAuthenticationToken
        authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails == null
            	? new ArrayList<>()
            	: userDetails.getAuthorities()
        );
        
        authentication.setDetails(new WebAuthenticationDetailsSource()
    		.buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}
}
