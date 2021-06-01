package br.com.bolao.bolaoPaulista.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bolao.bolaoPaulista.model.Team;

public class TeamDTO {
	
	private Long id;
	private String name;
	private String pathLogo;

	
	public TeamDTO() {
		// TODO Auto-generated constructor stub
	}
	public TeamDTO(Team team) {
		this.id = team.getId();
		this.name = team.getName();
		this.pathLogo = team.getPathLogo();
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

	public String getPathLogo() {
		return pathLogo;
	}
	
	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}
	
	public static List<TeamDTO> convertToDTO(List<Team> teams) {
	 return	teams.stream().map(TeamDTO::new).collect(Collectors.toList());	
	}

}
