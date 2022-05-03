package com.dio.santander.bankline.api.dto;

import com.dio.santander.bankline.api.model.MovimentacaoTipo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoDTO {
	
	private String descricao;
	private Double valor;
	private MovimentacaoTipo tipo;
	private Integer idConta;
	
}
