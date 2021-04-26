package br.com.bolao.bolaoPaulista.service;

import java.util.List;

import br.com.bolao.bolaoPaulista.dto.PalpiteDTO;
import br.com.bolao.bolaoPaulista.modelo.Palpite;

public interface PalpiteService{

	List<Palpite> buscarTodosPalpites();

	Palpite buscarPorId(Long id);

	Palpite cadastrar(PalpiteDTO palpiteDTO);

	Palpite alterar(PalpiteDTO palpiteDTO, Long id);

	boolean deletar(Long id);

}
