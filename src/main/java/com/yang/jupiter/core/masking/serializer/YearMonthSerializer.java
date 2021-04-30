package com.yang.jupiter.core.masking.serializer;

import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.yang.jupiter.core.masking.MaskAuthStore;
import com.yang.jupiter.core.masking.MaskJsonSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;


public class YearMonthSerializer extends MaskJsonSerializer<YearMonth> {
	private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM");

	public YearMonthSerializer(final ObjectMapper mapper) {
		super(mapper);
	}

	public YearMonthSerializer(final ObjectMapper mapper, final MaskAuthStore store) {
		super(mapper, store);
	}

	@Override
	public void serialize(final YearMonth value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		if(type != null) {
			super.serialize(value, gen, serializers);
			return;
		}

		gen.writeString(value.format(FORMAT));
	}


}
