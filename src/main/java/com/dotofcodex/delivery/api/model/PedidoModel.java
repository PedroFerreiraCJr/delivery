package com.dotofcodex.delivery.api.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PedidoModel {
	private Long id;
	private ClienteModel cliente;
	private BigDecimal total;
	private BigDecimal frete;
	private List<EntregaModel> status;
}
