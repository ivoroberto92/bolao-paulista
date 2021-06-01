package br.com.bolao.bolaoPaulista.serviceImpl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.TeamDTO;
import br.com.bolao.bolaoPaulista.model.Team;
import br.com.bolao.bolaoPaulista.repository.TeamRepository;
import br.com.bolao.bolaoPaulista.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

//	@Override
// Trocar modo de carregamento das imagens no futuro
//	public List<TeamDTO> decodeLogo(List<TeamDTO> teamsDTO) {
//		for (TeamDTO team : teamsDTO) {
//			System.out.println(team.getLogo());
//			if (team.getLogo() != null) {
//				String srcLogo = new String(Base64.getDecoder().decode(team.getLogo()), StandardCharsets.UTF_8);
//				team.setSrcLogo(srcLogo);
//			}
//		}
//		return teamsDTO;
//	}

}
