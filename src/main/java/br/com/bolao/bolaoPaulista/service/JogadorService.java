package br.com.bolao.bolaoPaulista.service;

import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.modelo.Jogador;

public interface JogadorService {

	Jogador buscarJogadorPorId(Long id);

	Jogador atribuirTimeAoJogador(String nomeTime, String nomeJogador);

}
