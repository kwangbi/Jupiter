package com.yang.jupiter.core.masking.serializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.yang.jupiter.core.masking.MaskAuthStore;
import com.yang.jupiter.core.masking.MaskJsonSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;


public class LocalDateTimeSerializer extends MaskJsonSerializer<LocalDateTime> {
	private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public LocalDateTimeSerializer(final ObjectMapper mapper) {
		super(mapper);
	}

	public LocalDateTimeSerializer(final ObjectMapper mapper, final MaskAuthStore store) {
		super(mapper, store);
	}

	@Override
	public void serialize(final LocalDateTime value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		if(type != null) {
			super.serialize(value, gen, serializers);
			return;
		}

		gen.writeString(value.format(FORMAT));
	}
}
