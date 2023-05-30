package com.dotofcodex.delivery.api.model;

import java.time.LocalDateTime;

import com.dotofcodex.delivery.model.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EntregaModel {
	private Status status;
	private LocalDateTime dataHora;
}
