package br.com.bolao.bolaoPaulista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.model.Player;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	Player findByName(String playerName);
	
	@Query("SELECT p FROM Player p ORDER BY p.score DESC")
	List<Player> findAllByScore();

}
