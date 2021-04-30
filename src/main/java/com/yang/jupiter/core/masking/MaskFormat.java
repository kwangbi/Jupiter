package com.yang.jupiter.core.masking;

public interface MaskFormat<T> {
	public final static char MASK = '*';

	public String  unknow(T target);

	public String low(T target);

	public String middle(T target);

	public String high(T target);

	public String least(T target);
}
