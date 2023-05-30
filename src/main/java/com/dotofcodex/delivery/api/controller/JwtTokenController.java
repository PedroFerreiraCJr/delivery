package com.dotofcodex.delivery.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dotofcodex.delivery.api.converter.ClienteConverter;
import com.dotofcodex.delivery.api.model.ClienteInput;
import com.dotofcodex.delivery.api.model.ClienteModel;
import com.dotofcodex.delivery.api.model.JwtTokenResponse;
import com.dotofcodex.delivery.api.model.UserInput;
import com.dotofcodex.delivery.core.JwtTokenUtil;
import com.dotofcodex.delivery.model.Cliente;
import com.dotofcodex.delivery.service.CadastroClienteService;

@RestController
@RequestMapping("/api")
public class JwtTokenController {

	@Autowired
	private CadastroClienteService service;
	
	@Autowired
	private ClienteConverter converter;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/auth")
	public ResponseEntity<JwtTokenResponse> autenticar(@RequestBody @Valid UserInput input) throws Exception {
		final Authentication auth = authenticate(input.getEmail(), input.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return ResponseEntity.ok(new JwtTokenResponse(jwtTokenUtil.generateToken(auth)));
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel registrar(@RequestBody @Valid ClienteInput input) {
		Cliente cliente = service.salvar(converter.toModel(input));
		return converter.toApi(cliente);
	}
	
	private Authentication authenticate(String email, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
