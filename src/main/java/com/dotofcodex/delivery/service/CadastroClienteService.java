package com.dotofcodex.delivery.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dotofcodex.delivery.api.model.ClienteInput;
import com.dotofcodex.delivery.exception.ClienteNaoEncontradoException;
import com.dotofcodex.delivery.model.Cliente;
import com.dotofcodex.delivery.repository.ClienteRepository;

@Service
public class CadastroClienteService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	public Cliente salvar(Cliente cliente) {
		cliente.setPassword(encoder.encode(cliente.getPassword()));
		return clienteRepository.save(cliente);
	}

	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
	}

	public Cliente atualizarCliente(Cliente cliente, ClienteInput input) {
		BeanUtils.copyProperties(input, cliente);

		return clienteRepository.save(cliente);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return clienteRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(""));
	}
}
