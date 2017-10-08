package br.com.caelum.estoque.execusaoSistema;

import javax.xml.ws.Endpoint;

import br.com.caelum.estoque.webservice.EstoqueWs;

public class ServidorSOAP {
	public static void main(String[] args) {
		
		EstoqueWs estoque = new EstoqueWs();
		
		String url = "http://localhost:8080/estoquews";
		
		Endpoint.publish(url, estoque);
	}
	
	
}
