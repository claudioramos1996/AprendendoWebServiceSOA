package br.com.caelum.estoque.webservice;

import java.util.List;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
@SOAPBinding(style=Style.DOCUMENT,use=Use.LITERAL,parameterStyle=ParameterStyle.WRAPPED)
public class EstoqueWs {

	private ItemDao dao = new ItemDao();

	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "item")
	@RequestWrapper(localName = "listaItens")
	@ResponseWrapper(localName = "itens")
	public List<Item> getItens(@WebParam(name = "filtros") Filtros filtros) {
		System.out.println("EFETUADA BUSCA POR ITENS");
		return dao.todosItens(filtros.getLista());
	}

	@WebMethod(exclude = true)
	public void outrosMetodo() {

	}

	@WebMethod(operationName = "cadastrarItem")
	@WebResult(name = "item")
	public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario token,
			@WebParam(name = "item") Item item) throws AutorizacaoException {

		System.out.println("Cadastrando Item, token: " + token);

		boolean validoToken = new TokenDao().ehValido(token);

		if (validoToken == false)
			throw new AutorizacaoException("Autorizacao falhou");

		this.dao.cadastrar(item);

		return item;
	}
	
	@Oneway
	@WebMethod(operationName="GerarRelatorio")
	public void gerarRelatorio() { 
	    // código omitido
	}

}
