package com.solucao.integracao.service.excepetion;

public class SenhaObrigatoriaUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaObrigatoriaUsuarioException(String msg) {
		super(msg);
	}
	
}
