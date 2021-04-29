package br.com.bolao.bolaoPaulista.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.PlayerDTO;
import br.com.bolao.bolaoPaulista.modelo.Player;
import br.com.bolao.bolaoPaulista.modelo.Team;
import br.com.bolao.bolaoPaulista.repository.PlayerRepository;
import br.com.bolao.bolaoPaulista.repository.TeamRepository;
import br.com.bolao.bolaoPaulista.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public Player createPlayer(String nomeTeam, String playerName) {
		Player player = null;
		Team team = teamRepository.findByName(nomeTeam);
		if (team != null) {
			player = new Player(playerName, team, 0);
			playerRepository.save(player);
			return player;
		}
		return player;
	}

	@Override
	public Player findPlayerById(Long id) {
		Player returnPlayer = null;
		Optional<Player> player = playerRepository.findById(id);
		if (player.isPresent()) {
			returnPlayer = player.get();
			return returnPlayer;
		}
		return returnPlayer;
	}

	@Override
	public List<Player> findAllPlayers() {
		return playerRepository.findAll();
	}
	
	@Override
	public Player updatePlayer(Long id, PlayerDTO playerDTO) {
		Player player = findPlayerById(id);
		Team team = teamRepository.getOne(playerDTO.getId());
		if (player != null && team != null) {
			player.setTeam(team);
			playerRepository.save(player);
			return player;
		}
		return player;
	}

	@Override
	public boolean removePlayerById(Long id) {
		Optional<Player> player = playerRepository.findById(id);
		if (player.isPresent()) {
			playerRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
