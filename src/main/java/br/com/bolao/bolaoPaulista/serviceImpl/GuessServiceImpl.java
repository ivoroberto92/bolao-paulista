package br.com.bolao.bolaoPaulista.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.GuessDTO;
import br.com.bolao.bolaoPaulista.model.Guess;
import br.com.bolao.bolaoPaulista.model.Player;
import br.com.bolao.bolaoPaulista.model.Team;
import br.com.bolao.bolaoPaulista.repository.GuessRepository;
import br.com.bolao.bolaoPaulista.repository.PlayerRepository;
import br.com.bolao.bolaoPaulista.repository.TeamRepository;
import br.com.bolao.bolaoPaulista.service.GuessService;

@Service
public class GuessServiceImpl implements GuessService {

	@Autowired
	private GuessRepository guessRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public List<Guess> findAllGuess() {
		return guessRepository.findAll();
	}

	@Override
	public Guess findGuessById(Long id) {
		Guess returnGuess = null;
		Optional<Guess> guess = guessRepository.findById(id);
		if (guess.isPresent()) {
			returnGuess = guess.get();
			return returnGuess;
		}
		return returnGuess;
	}

	@Override
	public Guess createGuess(GuessDTO guessDTO) {
		Guess guess = validGuessDTO(guessDTO);
			if (guess != null) {
				guessRepository.save(guess);
			}
		return guess;
	}
	
	private Guess validGuessDTO(GuessDTO guessDTO) {
		Guess guess = null;
		Player player = playerRepository.findByName(guessDTO.getPlayerName());
		Team homeTeam = teamRepository.findByName(guessDTO.getHomeTeam());
		Team awayTeam = teamRepository.findByName(guessDTO.getAwayTeam());
		if (player != null && homeTeam != null && awayTeam != null) {
			guess = new Guess(guessDTO.getId(), player, homeTeam, awayTeam, guessDTO.getGoalsHomeTeam(),
					guessDTO.getGoalsAwayTeam());
			return guess;
		}
		return guess;
	}

	@Override
	public Guess updateGuess(GuessDTO guessDTO, Long id) {
		Guess guess = null;
		// analisar como buscar player pelo id
		Player player = playerRepository.findByName(guessDTO.getPlayerName());
		Team homeTeam = teamRepository.findByName(guessDTO.getHomeTeam());
		Team awayTeam = teamRepository.findByName(guessDTO.getAwayTeam());
		if (player != null && homeTeam != null && awayTeam != null) {
			guess = guessRepository.getOne(id);
			guess.setPlayer(player);
			guess.setHomeTeam(homeTeam);
			guess.setAwayTeam(awayTeam);
			guess.setGoalsHomeTeam(guessDTO.getGoalsHomeTeam());
			guess.setGoalsAwayTeam(guessDTO.getGoalsAwayTeam());
			guessRepository.save(guess);
			return guess;
		}

		return guess;
	}

	@Override
	public boolean removeGuessById(Long id) {
		Optional<Guess> guess = guessRepository.findById(id);
		if (guess.isPresent()) {
			guessRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
