package com.dotofcodex.delivery.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClienteModel {
	private Long id;
	private String nome;
	private String email;
}
