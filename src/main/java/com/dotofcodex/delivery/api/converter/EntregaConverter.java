package com.dotofcodex.delivery.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dotofcodex.delivery.api.model.EntregaModel;
import com.dotofcodex.delivery.model.Entrega;

@Component
public class EntregaConverter {

	public List<EntregaModel> toApi(List<Entrega> lista) {
		return lista.stream().map((e) -> {
			return EntregaModel.builder()
				.status(e.getStatus())
				.dataHora(e.getDataHora())
				.build();
		}).collect(Collectors.toList());
	}
}
