package br.com.bolao.bolaoPaulista.service;

import java.util.List;

import br.com.bolao.bolaoPaulista.dto.GuessDTO;
import br.com.bolao.bolaoPaulista.model.Guess;

public interface GuessService{

	List<Guess> findAllGuess();

	Guess findGuessById(Long id);

	Guess createGuess(GuessDTO guessDTO);

	Guess updateGuess(GuessDTO guessDTO, Long id);

	boolean removeGuessById(Long id);

}
