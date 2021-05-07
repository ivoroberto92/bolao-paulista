package br.com.bolao.bolaoPaulista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.model.Player;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	Player findByName(String playerName);

}
