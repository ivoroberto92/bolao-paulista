package br.com.bolao.bolaoPaulista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.model.Guess;
@Repository
public interface GuessRepository extends JpaRepository<Guess, Long>{
	@Query("SELECT g FROM Guess g WHERE g.player = :id")
	List<Guess> findAllGuessesByPlayerId(Long id);

}
