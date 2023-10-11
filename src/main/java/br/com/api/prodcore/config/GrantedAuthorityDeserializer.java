package br.com.api.prodcore.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.api.prodcore.model.Role;

public class GrantedAuthorityDeserializer extends JsonDeserializer<GrantedAuthority>{

	@Override
	public GrantedAuthority deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		JsonNode node = p.getCodec().readTree(p);
		
		GrantedAuthority authorities;
			if(node.has("authority")) {
				authorities = new SimpleGrantedAuthority(node.get("authority").asText());
			}else {
				authorities = ctxt.readValue(node.traverse(), ctxt.constructType(Role.class));
			}
		
		return authorities;
	}

}
