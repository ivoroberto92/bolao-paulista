package br.com.bolao.bolaoPaulista.service;

import java.util.List;
import java.util.Optional;

import br.com.bolao.bolaoPaulista.dto.PlayerDTO;
import br.com.bolao.bolaoPaulista.model.Player;

public interface PlayerService {

	Player findPlayerById(Long id);

	Player createPlayer(String nomeTime, String playerName);

	Player updatePlayer(Long id, PlayerDTO playerDTO);

	List<Player> findAllPlayers();

	boolean removePlayerById(Long id);

}
