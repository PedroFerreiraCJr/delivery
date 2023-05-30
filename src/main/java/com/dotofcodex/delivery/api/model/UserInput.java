package com.dotofcodex.delivery.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {
	@NotBlank
	private String email;

	@NotBlank
	private String password;
}
