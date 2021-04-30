package com.yang.jupiter.core.masking;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.yang.jupiter.core.masking.dto.MaskingRuleDTO;

public class MaskFilter {
	public final static String KEY = "x-mask-auth";
	
	@SuppressWarnings("unchecked")
	public static <T> String doFilter(final MaskType type, final boolean hasAuth, final T target) {
		MaskFormat<T> maskFormat = (MaskFormat<T>) type.getFormat();
		if(target instanceof String && StringUtils.isEmpty((String)target)) {
			return StringUtils.EMPTY;
		}
		if(target == null) {
			return StringUtils.EMPTY;
		}
		
		// redis에서 상태값 가져오기
		MaskingRuleDTO maskRule = new MaskingRuleDTO();
		maskRule.setMskCd("my012");
		maskRule.setUseYn("Y");
		maskRule.setRlseYn("Y");
		maskRule.setFmtTp("LO");
		
		if(isValidRule(maskRule) == false) {
			return maskFormat.unknow(target);
		}

		String useYn = maskRule.getUseYn()
				, rlseYn = maskRule.getRlseYn()
				, fmtTp = maskRule.getFmtTp();
		if(StringUtils.equals(useYn, "Y") == false) {
			return maskFormat.least(target);
		}


		if(hasAuth == false || StringUtils.equals(rlseYn, "N")) {
			if(StringUtils.equals(fmtTp, "HI")) {
				return maskFormat.high(target);
			}
			if(StringUtils.equals(fmtTp, "MI")) {
				return maskFormat.middle(target);
			}
			return maskFormat.low(target);
		}

		if(hasAuth && StringUtils.equals(rlseYn, "Y")) {
			return maskFormat.least(target);
		}

		return maskFormat.unknow(target);
	}
	
	public static <T> String doFilter(final MaskType type, final T target) {
		Boolean hasAuth = (Boolean) RequestContextHolder.getRequestAttributes()
				.getAttribute( MaskFilter.KEY, RequestAttributes.SCOPE_REQUEST);
		return doFilter(type, hasAuth !=null && hasAuth, target);
	}

	private static boolean isValidRule(final MaskingRuleDTO maskRule) {
		return maskRule != null
				&& StringUtils.isNotEmpty(maskRule.getUseYn())
				&& StringUtils.isNotEmpty(maskRule.getRlseYn())
				&& StringUtils.isNotEmpty(maskRule.getFmtTp());
	}

}
