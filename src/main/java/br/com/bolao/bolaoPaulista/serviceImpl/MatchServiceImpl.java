package br.com.bolao.bolaoPaulista.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.MatchDTO;
import br.com.bolao.bolaoPaulista.model.Guess;
import br.com.bolao.bolaoPaulista.model.Match;
import br.com.bolao.bolaoPaulista.model.Team;
import br.com.bolao.bolaoPaulista.repository.MatchRepository;
import br.com.bolao.bolaoPaulista.repository.TeamRepository;
import br.com.bolao.bolaoPaulista.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchRepository matchRepository;
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public List<Match> findAllMatches() {
		return matchRepository.findAll();
	}

	@Override
	public Match findMatchById(Long id) {
		Match returnMatch = null;
		Optional<Match> match = matchRepository.findById(id);
		if (match.isPresent()) {
			returnMatch = match.get();
			return returnMatch;
		}
		return returnMatch;
	}

	@Override
	public Match createMatch(MatchDTO matchDTO, String nameHomeTeam, String nameAwayTeam) {
		Match match = null;
		if (!nameHomeTeam.equals(nameAwayTeam)) {
			Team homeTeam = teamRepository.findByName(nameHomeTeam);
			Team awayTeam = teamRepository.findByName(nameAwayTeam);
			if (homeTeam != null && awayTeam != null) {
				match = new Match(matchDTO.getId(), homeTeam, awayTeam, matchDTO.getGoalsHomeTeam(),
						matchDTO.getGoalsAwayTeam());
				matchRepository.save(match);
				return match;
			}
		}

		return match;
	}

	@Override
	public Match updateMatch(Long id, MatchDTO matchDTO) {
		Match match = findMatchById(id);
		Team homeTeam = teamRepository.findByName(matchDTO.getHomeTeam());
		Team awayTeam = teamRepository.findByName(matchDTO.getAwayTeam());
		if (match != null) {
			match.setHomeTeam(homeTeam);
			match.setAwayTeam(awayTeam);
			match.setGoalsHomeTeam(matchDTO.getGoalsHomeTeam());
			match.setGoalsAwayTeam(matchDTO.getGoalsAwayTeam());
			matchRepository.save(match);
			return match;
		}
		return match;
	}

	@Override
	public boolean removeMatchById(Long id) {
		Optional<Match> match = matchRepository.findById(id);
		if (match.isPresent()) {
			matchRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Match findByTeams(Guess guess) {
		Match match = matchRepository.findByTeams(guess.getHomeTeam(), guess.getAwayTeam());
		if (match.getAwayTeam() == guess.getAwayTeam() && match.getHomeTeam() == guess.getHomeTeam()) {
			return match;
		}
		return null;
	}
}
