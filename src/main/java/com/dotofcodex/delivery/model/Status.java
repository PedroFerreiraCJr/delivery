package com.dotofcodex.delivery.model;

public enum Status {
	CRIADO("Criado"), CONFIRMADO("Confirmado"), CANCELADO("Cancelado"), A_CAMINHO("A Caminho"), ENTREGUE("Entregue");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}
	
	public Status proximo() {
		if (this == CRIADO) {
			return CONFIRMADO;
		}
		
		if (this == CONFIRMADO) {
			return A_CAMINHO;
		}
		
		if (this == A_CAMINHO) {
			return ENTREGUE;
		}
		
		return null;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
