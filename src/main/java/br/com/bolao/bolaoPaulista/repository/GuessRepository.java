package br.com.bolao.bolaoPaulista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.model.Guess;
@Repository
public interface GuessRepository extends JpaRepository<Guess, Long>{

}
