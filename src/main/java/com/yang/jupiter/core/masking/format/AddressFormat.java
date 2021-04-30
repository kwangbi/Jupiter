package com.yang.jupiter.core.masking.format;

import org.apache.commons.lang3.StringUtils;

import com.yang.jupiter.core.masking.MaskFormat;

public class AddressFormat extends BaseMaskFormat<String> {

	public final static MaskFormat<String> INSTANCE = new AddressFormat();
	private final String MASK2 = StringUtils.repeat(MASK, 2);
	private final String MASK5 = StringUtils.repeat(MASK, 5);
	private final String MASK8 = StringUtils.repeat(MASK, 8);

	@Override
	public String low(final String target) {
		return StringUtils.left(target, 10) + MASK2;
	}

	@Override
	public String middle(final String target) {
		return StringUtils.left(target, 7) + MASK5;
	}

	@Override
	public String high(final String target) {
		return StringUtils.left(target, 4) + MASK8;
	}

}
