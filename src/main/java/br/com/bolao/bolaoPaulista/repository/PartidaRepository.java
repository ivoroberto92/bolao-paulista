package br.com.bolao.bolaoPaulista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.modelo.Partida;
@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long>{

}
