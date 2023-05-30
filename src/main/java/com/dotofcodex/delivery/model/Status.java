package com.dotofcodex.delivery.model;

public enum Status {
	CRIADO("Criado"), CONFIRMADO("Confirmado"), CANCELADO("Cancelado"), A_CAMINHO("A Caminho"), ENTREGUE("Entregue");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
