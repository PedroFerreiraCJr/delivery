package com.dotofcodex.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ClienteNaoEncontradoException(Long cidadeId) {
		this(String.format("Cliente de código %d não encontrado", cidadeId));
	}
}
