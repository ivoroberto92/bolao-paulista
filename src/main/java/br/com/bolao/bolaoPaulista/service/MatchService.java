package br.com.bolao.bolaoPaulista.service;

import java.util.List;

import br.com.bolao.bolaoPaulista.dto.MatchDTO;
import br.com.bolao.bolaoPaulista.model.Guess;
import br.com.bolao.bolaoPaulista.model.Match;
import br.com.bolao.bolaoPaulista.model.Team;

public interface MatchService {

	List<Match> findAllMatches();

	Match findMatchById(Long id);

	Match createMatch(MatchDTO matchDTO);

	Match updateMatch(Long id, MatchDTO matchDTO);

	boolean removeMatchById(Long id);

	Match findByTeams(Guess guess);

	List<Team> findAllTeams();
}
