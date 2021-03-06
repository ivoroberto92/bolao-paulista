package br.com.bolao.bolaoPaulista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.model.Team;
@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	Team findByName(String teamName);
}
