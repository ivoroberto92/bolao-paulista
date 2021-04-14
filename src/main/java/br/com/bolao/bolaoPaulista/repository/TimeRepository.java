package br.com.bolao.bolaoPaulista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.modelo.Time;
@Repository
public interface TimeRepository extends JpaRepository<Time, Long>{

	Time findByNome(String nomeTime);

}
