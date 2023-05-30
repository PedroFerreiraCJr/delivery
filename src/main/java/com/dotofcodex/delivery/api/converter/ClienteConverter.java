package com.dotofcodex.delivery.api.converter;

import org.springframework.stereotype.Component;

import com.dotofcodex.delivery.api.model.ClienteInput;
import com.dotofcodex.delivery.api.model.ClienteModel;
import com.dotofcodex.delivery.model.Cliente;

@Component
public class ClienteConverter implements Converter<Cliente, ClienteInput, ClienteModel> {

	@Override
	public Cliente toModel(ClienteInput input) {
		return Cliente.builder()
			.nome(input.getNome())
			.email(input.getEmail())
			.password(input.getPassword())
			.build();
	}

	@Override
	public ClienteModel toApi(Cliente model) {
		return ClienteModel.builder()
			.id(model.getId())
			.nome(model.getNome())
			.email(model.getEmail())
			.build();
	}
}
