package com.yang.jupiter.core.masking;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class MaskJsonSerializer<T> extends JsonSerializer<T> implements ContextualSerializer {
	
	protected MaskType type = null;
	private ObjectMapper mapper = null;
	private MaskAuthStore store;
	
	public MaskJsonSerializer(final ObjectMapper mapper) {
		this(mapper, null);
	}

	public MaskJsonSerializer(final ObjectMapper mapper, final MaskAuthStore store) {
		this(mapper, store, null);
	}

	public MaskJsonSerializer(final ObjectMapper mapper, final MaskAuthStore store, final MaskType type) {
		this.mapper = mapper;
		this.store = store;
		this.type = type;
	}
	
	@Override
	public void serialize(final T value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		if(type != null) {
			boolean hasAuth = store != null && store.hasAuth();
			gen.writeString(MaskFilter.doFilter(type, hasAuth, value));
			return;
		}

		mapper.writeValue(gen, value);
	}

	@Override
	public MaskJsonSerializer<T> createContextual(final SerializerProvider prov, final BeanProperty property)
			throws JsonMappingException {

		if(property == null) {
			return new MaskJsonSerializer<>(mapper, store);
		}

		Maskable ann = property.getAnnotation(Maskable.class);
		if(ann == null) {
			return this;
		}

		return new MaskJsonSerializer<>(mapper, store, ann.value());
	}

}
