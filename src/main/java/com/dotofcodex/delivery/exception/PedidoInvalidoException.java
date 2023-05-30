package com.dotofcodex.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoInvalidoException(String mensagem) {
		super(mensagem);
	}

	public PedidoInvalidoException(Long cidadeId) {
		this(String.format("Cliente de código %d não encontrado", cidadeId));
	}
}
