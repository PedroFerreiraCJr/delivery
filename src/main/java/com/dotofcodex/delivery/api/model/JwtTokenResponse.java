package com.dotofcodex.delivery.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtTokenResponse {
	private String token;
}
