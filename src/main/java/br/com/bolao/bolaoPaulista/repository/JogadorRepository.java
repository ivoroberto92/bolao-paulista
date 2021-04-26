package br.com.bolao.bolaoPaulista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bolao.bolaoPaulista.modelo.Jogador;
@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

	Jogador findByNome(String nomeJogador);

}
