package com.dotofcodex.delivery.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotofcodex.delivery.api.converter.EntregaConverter;
import com.dotofcodex.delivery.api.model.EntregaModel;
import com.dotofcodex.delivery.model.Pedido;
import com.dotofcodex.delivery.service.CadastroPedidoService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private CadastroPedidoService service;

	@Autowired
	private EntregaConverter converter;

	@GetMapping("/{pedidoId}")
	public List<EntregaModel> buscarPorId(@PathVariable("pedidoId") Long pedidoId) {
		Pedido pedido = service.buscarOuFalhar(pedidoId);

		return converter.toApi(pedido.getStatus());
	}
}
