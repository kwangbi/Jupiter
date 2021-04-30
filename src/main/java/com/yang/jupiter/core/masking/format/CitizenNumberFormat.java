package com.yang.jupiter.core.masking.format;

import org.apache.commons.lang3.StringUtils;

import com.yang.jupiter.core.masking.MaskFormat;

public class CitizenNumberFormat extends BaseMaskFormat<String> {
	public final static MaskFormat<String> INSTANCE = new CitizenNumberFormat();

	@Override
	public String low(final String target) {
		return maskTail(target, 6);
	}

	@Override
	public String middle(final String target) {
		return StringUtils.repeat(MASK, 2)
				+ StringUtils.mid(target, 2, 4)
				+ StringUtils.repeat(MASK, target.length() -6);
	}

	@Override
	public String high(final String target) {
		return maskAll(target);
	}

	@Override
	public String least(final String target) {
		return middle(target);
	}

}
