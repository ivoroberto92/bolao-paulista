package br.com.bolao.bolaoPaulista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.model.Match;
import br.com.bolao.bolaoPaulista.model.Team;
@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
	
	@Query("SELECT m FROM Match m WHERE m.homeTeam = homeTeam and m.awayTeam = awayTeam")
	Match findByTeams(Team homeTeam, Team awayTeam);


}
