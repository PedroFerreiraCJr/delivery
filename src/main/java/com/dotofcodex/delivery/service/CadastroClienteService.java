package com.dotofcodex.delivery.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotofcodex.delivery.api.model.ClienteInput;
import com.dotofcodex.delivery.exception.ClienteNaoEncontradoException;
import com.dotofcodex.delivery.model.Cliente;
import com.dotofcodex.delivery.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	public Cliente salvar(Cliente cliente) {
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
}
