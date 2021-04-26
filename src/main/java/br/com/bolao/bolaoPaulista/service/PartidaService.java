package br.com.bolao.bolaoPaulista.service;

import java.util.List;

import br.com.bolao.bolaoPaulista.dto.PartidaDTO;
import br.com.bolao.bolaoPaulista.modelo.Partida;

public interface PartidaService {

	List<Partida> buscarTodasPartidas();

	Partida buscarPartidaPorId(Long id);

	Partida atribuirTimeNaPartida(PartidaDTO partidaDTO, String timeCasa, String timeVisitante);

	Partida alterarPartida(Long id, PartidaDTO partidaDTO);

	boolean deletarPartida(Long id);
}
