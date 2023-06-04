package com.dotofcodex.delivery.core.messages;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Messages {

	private final MessageSource messageSource;

	public String getMessage(String key, String defaultMessage) {
		return messageSource.getMessage(key, null, defaultMessage, Locale.US);
	}
}
