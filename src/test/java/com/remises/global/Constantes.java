package com.remises.global;

public enum Constantes {

	URI("http://localhost:8080/remises-backend/");
	
	private String url;
	
	Constantes(String url) {
		this.url = url;
	}
	
	public String url() {
		return url;
	}

}
