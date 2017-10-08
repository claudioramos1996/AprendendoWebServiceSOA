package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault")
public class AutorizacaoException extends Exception {

	public AutorizacaoException(String mensagem) {
		// TODO Auto-generated constructor stub
		super(mensagem);
	}
	
	public InfoFault getFaultInfo(){
		return new InfoFault("Token invalido", new Date());
	}

}
