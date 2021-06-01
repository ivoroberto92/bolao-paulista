package br.com.bolao.bolaoPaulista.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.bolao.bolaoPaulista.model.Guess;

public class GuessesByDateDTO {
	
	private LocalDate date;

	private List<Guess> guesses;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<Guess> getGuesses() {
		return guesses;
	}
	public void setGuesses(List<Guess> guesses) {
		this.guesses = guesses;
	}
	
	public GuessesByDateDTO(LocalDate date, List<Guess> guesses) {
		super();
		this.date = date;
		this.guesses = guesses;
	}
	
	public static List<GuessesByDateDTO> convertGuess(Map<LocalDate, List<Guess>> guessesByDate) {
		return guessesByDate.entrySet().stream().map(x ->
		 new GuessesByDateDTO(x.getKey(), x.getValue())).collect(Collectors.toList());
	}
}
