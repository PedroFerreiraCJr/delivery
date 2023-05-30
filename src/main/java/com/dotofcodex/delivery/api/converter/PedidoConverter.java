package com.dotofcodex.delivery.api.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dotofcodex.delivery.api.model.PedidoInput;
import com.dotofcodex.delivery.api.model.PedidoModel;
import com.dotofcodex.delivery.model.Cliente;
import com.dotofcodex.delivery.model.Pedido;

@Component
public class PedidoConverter implements Converter<Pedido, PedidoInput, PedidoModel>{

	@Autowired
	private ClienteConverter clienteConverter;
	
	@Autowired
	private EntregaConverter entregaConverter;
	
	@Override
	public Pedido toModel(PedidoInput input) {
		Cliente cliente = new Cliente();
		cliente.setId(input.getClienteId());

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);

		return pedido;
	}

	@Override
	public PedidoModel toApi(Pedido model) {
		return PedidoModel.builder()
			.id(model.getId())
			.frete(model.getFrete())
			.total(model.getTotal())
			.cliente(clienteConverter.toApi(model.getCliente()))
			.status(entregaConverter.toApi(model.getStatus()))
			.build();
	}
}
