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

import com.dotofcodex.delivery.api.converter.PedidoConverter;
import com.dotofcodex.delivery.api.model.PedidoInput;
import com.dotofcodex.delivery.api.model.PedidoModel;
import com.dotofcodex.delivery.model.Pedido;
import com.dotofcodex.delivery.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private CadastroPedidoService pedidoService;

	@Autowired
	private PedidoConverter converter;

	@GetMapping
	public List<PedidoModel> listar() {
		return pedidoService.buscarTodos().stream()
				.map(converter::toApi).collect(Collectors.toList());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput input) {
		Pedido pedido = pedidoService.cadastrar(converter.toModel(input));
		return converter.toApi(pedido);
	}
	
	@PutMapping("/{id}/{status}")
	public PedidoModel atualizarStatusPedido(@PathVariable("id") Long id, @PathVariable("status") String status) {
		Pedido pedido = pedidoService.atualizarStatus(id, status);
		return converter.toApi(pedido);
	}
}
