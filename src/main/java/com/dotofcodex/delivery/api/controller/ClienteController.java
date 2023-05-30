package com.dotofcodex.delivery.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dotofcodex.delivery.api.converter.ClienteConverter;
import com.dotofcodex.delivery.api.model.ClienteInput;
import com.dotofcodex.delivery.api.model.ClienteModel;
import com.dotofcodex.delivery.model.Cliente;
import com.dotofcodex.delivery.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private CadastroClienteService clienteService;

	@Autowired
	private ClienteConverter converter;

	@GetMapping
	public List<ClienteModel> listar() {
		return clienteService.buscarTodos()
				.stream().map(converter::toApi).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ClienteModel buscar(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.buscarOuFalhar(id);

		return converter.toApi(cliente);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput input) {
		Cliente cliente = converter.toModel(input);

		cliente = clienteService.salvar(cliente);

		return converter.toApi(cliente);
	}

	@PutMapping("/{id}")
	public ClienteModel atualizar(@PathVariable("id") Long id, @RequestBody @Valid ClienteInput input) {
		Cliente cliente = clienteService.buscarOuFalhar(id);

		cliente = clienteService.atualizarCliente(cliente, input);

		return converter.toApi(cliente);
	}
}
