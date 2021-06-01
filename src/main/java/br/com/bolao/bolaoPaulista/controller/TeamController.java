package br.com.bolao.bolaoPaulista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bolao.bolaoPaulista.dto.TeamDTO;
import br.com.bolao.bolaoPaulista.model.Team;
import br.com.bolao.bolaoPaulista.service.TeamService;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "*")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	
	@GetMapping
	public List<TeamDTO> listAll(){
		List<Team> teams = teamService.findAll();
		List<TeamDTO> teamsDTO = TeamDTO.convertToDTO(teams);
		return teamsDTO;
//		return teamService.decodeLogo(teamsDTO);
	}
	

}
