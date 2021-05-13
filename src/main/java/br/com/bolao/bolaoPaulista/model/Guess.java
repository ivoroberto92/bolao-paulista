package br.com.bolao.bolaoPaulista.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Guess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "awayTeam_id")
	private Team awayTeam;

	@ManyToOne
	@JoinColumn(name = "homeTeam_id")
	private Team homeTeam;

	private int goalsHomeTeam;

	private int goalsAwayTeam;

	public Guess() {
	}

	public Guess(Long id, Player player, Team homeTeam, Team awayTeam, int goalsHomeTeam, int goalsAwayTeam) {
		this.id = id;
		this.player = player;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.goalsHomeTeam = goalsHomeTeam;
		this.goalsAwayTeam = goalsAwayTeam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
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

}
