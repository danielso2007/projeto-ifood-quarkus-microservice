package br.com.github.danielso.ifood.marketplace.dto;

import java.math.BigDecimal;

public class PratoPedidoDTO {
	
	public String nome;
	public String descricao;
	public BigDecimal preco;

	public PratoPedidoDTO() {
	}

	public PratoPedidoDTO(String nome, String descricao, BigDecimal preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
}
