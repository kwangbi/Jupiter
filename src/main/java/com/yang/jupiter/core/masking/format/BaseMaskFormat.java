package com.yang.jupiter.core.masking.format;

import org.apache.commons.lang3.StringUtils;

import com.yang.jupiter.core.masking.MaskFormat;

public abstract class BaseMaskFormat<T> implements MaskFormat<T> {
	private final String MASK4 = StringUtils.repeat(MASK, 4);
	
	@Override
	public String unknow(final T target) {
		return MASK4;
	}

	@Override
	public abstract String low(T target);
	@Override
	public abstract String middle(T target);
	@Override
	public abstract String high(T target);

	@Override
	public String least(final T target) {
		return target == null ? null: target.toString();
	}

	protected String maskTail(final String input, final int visibleChars) {
		int len = StringUtils.length(input);

		return StringUtils.left(input, visibleChars)
				+ StringUtils.repeat(MASK, len - visibleChars);
	}

	protected String maskAll(final String input) {
		int len = StringUtils.length(input);
		return StringUtils.repeat(MASK, len);
	}

	protected String maskHead(final String input, final int maskChars) {
		return StringUtils.repeat(MASK, maskChars)
				+ StringUtils.substring(input, maskChars);
	}	

}
