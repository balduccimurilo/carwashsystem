package com.tcc.lavarapido.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tcc.lavarapido.repositories.UserRepository;



public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UserRepository repository;
	


	public AutenticacaoViaTokenFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		boolean isValid = tokenService.isTokenValido(token);

		if (isValid)
			autenticarCliente(token);
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);


		repository.findById(idUsuario).ifPresent(guest ->
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(guest, null, guest.getAuthorities()))
		);

	}

	private String recuperarToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7);
	}

}
