package br.com.bolao.bolaoPaulista.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bolao.bolaoPaulista.model.Player;

public class PlayerDTO {

	private Long id;
	private String name;
	private String teamName;
	private Long teamId;
	private int score;

	public PlayerDTO() {
	}

	public PlayerDTO(Player player) {
		this.id = player.getId();
		this.name = player.getName();
		this.teamName = player.getTeam().getName();
		this.teamId = player.getTeam().getId();
		this.score = player.getScore();
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static List<PlayerDTO> converterParaDTO(List<Player> players) {
		return players.stream().map(PlayerDTO::new).collect(Collectors.toList());
	}

}
