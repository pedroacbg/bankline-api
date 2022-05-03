package com.dio.santander.bankline.api.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.santander.bankline.api.dto.MovimentacaoDTO;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.model.MovimentacaoTipo;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private CorrentistaRepository correntistaRepository;
	
	public void save(MovimentacaoDTO entity) {
		Movimentacao movimentacao = new Movimentacao();
		
		Double valor = entity.getTipo() == MovimentacaoTipo.RECEITA ? entity.getValor() : entity.getValor() * -1;
		
		movimentacao.setDataHora(LocalDateTime.now());
		movimentacao.setDescricao(entity.getDescricao());
		movimentacao.setId(entity.getIdConta());
		movimentacao.setTipo(entity.getTipo());
		movimentacao.setValor(valor);
		
		Correntista obj = correntistaRepository.findById(entity.getIdConta()).orElse(null);
		if(obj != null) {
			obj.getConta().setSaldo(obj.getConta().getSaldo() + valor);
			correntistaRepository.save(obj);
		}
		
		repository.save(movimentacao);
	}
	
}
