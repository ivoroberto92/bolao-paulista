package br.com.bolao.bolaoPaulista.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bolao.bolaoPaulista.model.Match;
import br.com.bolao.bolaoPaulista.model.Team;

public class MatchDTO {

	private Long id;
	private String homeTeam;
	private String awayTeam;
	private int goalsHomeTeam;
	private int goalsAwayTeam;
	private List<Team> teams;

	public MatchDTO() {
	}
	
	public MatchDTO(Match match) {
		this.id = match.getId();
		this.awayTeam = match.getAwayTeam().getName();
		this.homeTeam = match.getHomeTeam().getName();
		this.goalsHomeTeam = match.getGoalsHomeTeam();
		this.goalsAwayTeam = match.getGoalsAwayTeam();
	}
	
	public MatchDTO(Match match, List<Team> teams) {
		this.id = match.getId();
		this.awayTeam = match.getAwayTeam().getName();
		this.homeTeam = match.getHomeTeam().getName();
		this.goalsHomeTeam = match.getGoalsHomeTeam();
		this.goalsAwayTeam = match.getGoalsAwayTeam();
		this.teams = teams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static List<MatchDTO> converterParaDTO(List<Match> matches) {
		return matches.stream().map(MatchDTO::new).collect(Collectors.toList());
	}
}
