package br.com.bolao.bolaoPaulista.service;

import java.util.List;
import java.util.Optional;

import br.com.bolao.bolaoPaulista.dto.JogadorDTO;
import br.com.bolao.bolaoPaulista.modelo.Jogador;

public interface JogadorService {

	Jogador buscarJogadorPorId(Long id);

	Jogador atribuirTimeAoJogador(String nomeTime, String nomeJogador);

	Optional<Jogador> findById(Long id);

	Jogador alterarJogador(Long id, JogadorDTO jogadorDTO);

	List<Jogador> BuscarTodosJogadores();

	boolean deletarJogadorPorId(Long id);

}
