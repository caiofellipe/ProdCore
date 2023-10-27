package br.com.api.prodcore.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class UtilDatas {

	public LocalDateTime dataAtual() {
		return LocalDateTime.now().atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();
	}
	
	public LocalDateTime expiracaoPlanoAcesso() {
		// mudar para 30 dias.
		return LocalDateTime.now().plusDays(1).atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();
	}
}
