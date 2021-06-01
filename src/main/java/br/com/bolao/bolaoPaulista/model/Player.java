package br.com.bolao.bolaoPaulista.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "teamId")
	private Team team;

	private int score;

	private int position;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status")
	private Status status;

	private int guessRight;

	private int guessError;

	public Player() {
	}

	public Player(String name, Team team, int score, Status status) {
		this.name = name;
		this.team = team;
		this.score = score;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getGuessRight() {
		return guessRight;
	}

	public void setGuessRight(int guessRight) {
		this.guessRight = guessRight;
	}

	public int getGuessError() {
		return guessError;
	}

	public void setGuessError(int guessError) {
		this.guessError = guessError;
	}

}
