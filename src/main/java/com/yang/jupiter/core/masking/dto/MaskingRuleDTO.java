package com.yang.jupiter.core.masking.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class MaskingRuleDTO {

	/**
	 * 마스킹 코드
	 */
	@Id
	private String mskCd = "";

	/**
	 * 사용 여부
	 */
	private String useYn = "";

	/**
	 * 2차인증 해제 여부
	 */
	private String rlseYn = "";

	/**
	 * 포멧 타입
	 */
	private String fmtTp = "";

	/**
	 * Default Constructor for Redis
	 */
	public MaskingRuleDTO() {
		super();
	}

}
