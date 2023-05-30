package com.dotofcodex.delivery.api.converter;

public interface Converter<T, E, V> {
	public abstract T toModel(E input);
	public abstract V toApi(T model);
}
