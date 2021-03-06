package br.com.bolao.bolaoPaulista.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bolao.bolaoPaulista.model.Guess;

public class GuessDTO {

	private Long id;
	private String playerName;
	private String awayTeam;
	private String homeTeam;
	private int goalsHomeTeam;
	private int goalsAwayTeam;
	private LocalDate date;
	private int score;

	public GuessDTO(Guess guess) {
		this.id = guess.getId();
		// this.idPlayer = guess.getPlayer().getId();
		this.playerName = guess.getPlayer().getName();
		this.awayTeam = guess.getAwayTeam().getName();
		this.homeTeam = guess.getHomeTeam().getName();
		this.goalsHomeTeam = guess.getGoalsHomeTeam();
		this.goalsAwayTeam = guess.getGoalsAwayTeam();
		this.date = guess.getDate();
		this.score = guess.getPlayer().getScore();
	}

	public GuessDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getIdPlayer() {
//		return idPlayer;
//	}
//
//	public void setIdPlayer(Long idPlayer) {
//		this.idPlayer = idPlayer;
//	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public int getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(int goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	public int getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(int goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static List<GuessDTO> converterGuess(List<Guess> guesss) {
		return guesss.stream().map(GuessDTO::new).collect(Collectors.toList());
	}
}
