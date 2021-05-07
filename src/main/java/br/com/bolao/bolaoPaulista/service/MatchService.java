package br.com.bolao.bolaoPaulista.service;

import java.util.List;

import br.com.bolao.bolaoPaulista.dto.MatchDTO;
import br.com.bolao.bolaoPaulista.model.Match;

public interface MatchService {

	List<Match> findAllMatches();

	Match findMatchById(Long id);

	Match createMatch(MatchDTO matchDTO, String nameHomeTeam, String nameAwayTeam);

	Match updateMatch(Long id, MatchDTO matchDTO);

	boolean removeMatchById(Long id);
}
