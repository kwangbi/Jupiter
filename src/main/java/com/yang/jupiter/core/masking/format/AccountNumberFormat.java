package com.yang.jupiter.core.masking.format;

import org.apache.commons.lang3.StringUtils;

import com.yang.jupiter.core.masking.MaskFormat;

public class AccountNumberFormat extends BaseMaskFormat<String> {

	public final static MaskFormat<String> INSTANCE = new AccountNumberFormat();
	private final String MAXINUM = StringUtils.repeat(MASK, 14);

	@Override
	public String low(final String target) {
		String targetNoDash = StringUtils.remove(target, '-');
		return maskTail(targetNoDash, 6);
	}

	@Override
	public String middle(final String target) {
		String targetNoDash = StringUtils.remove(target, '-');
		return maskAll(targetNoDash);
	}

	@Override
	public String high(final String target) {
		return MAXINUM;
	}

	@Override
	public String least(final String target) {
		return low(target);
	}

}
