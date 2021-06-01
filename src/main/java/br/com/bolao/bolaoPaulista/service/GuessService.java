package br.com.bolao.bolaoPaulista.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import br.com.bolao.bolaoPaulista.dto.GuessDTO;
import br.com.bolao.bolaoPaulista.model.Guess;

public interface GuessService{

	List<Guess> findAllGuess();

	Guess findGuessById(Long id);

	Guess createGuess(GuessDTO guessDTO);

	Guess updateGuess(GuessDTO guessDTO, Long id);

	boolean removeGuessById(Long id);
	
	Map<LocalDate, List<Guess>> findAllGuessByDate();
}
