package br.com.bolao.bolaoPaulista.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.JogadorDTO;
import br.com.bolao.bolaoPaulista.modelo.Jogador;
import br.com.bolao.bolaoPaulista.modelo.Time;
import br.com.bolao.bolaoPaulista.repository.JogadorRepository;
import br.com.bolao.bolaoPaulista.repository.TimeRepository;
import br.com.bolao.bolaoPaulista.service.JogadorService;

@Service
public class JogadorServiceImpl implements JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;
	@Autowired
	private TimeRepository timeRepository;

	@Override
	public Jogador buscarJogadorPorId(Long id) {
		Jogador retornoJogador = null;
		Optional<Jogador> jogador = jogadorRepository.findById(id);
		if (jogador.isPresent()) {
			retornoJogador = jogador.get();
			return retornoJogador;
		}
		return retornoJogador;
	}

	@Override
	public Jogador cadastrarJogador(String nomeTime, String nomeJogador) {
		Jogador jogador = null;
		Time time = timeRepository.findByNome(nomeTime);
		if (time != null) {
			jogador = new Jogador(nomeJogador, time);
			jogadorRepository.save(jogador);
			return jogador;
		}
		return jogador;
	}

	@Override
	public Jogador findById(Long id) {
		Jogador retornoJogador = null;
		Optional<Jogador> jogador = jogadorRepository.findById(id);
		if (jogador.isPresent()) {
			retornoJogador = jogador.get();
			return retornoJogador;
		}
		return retornoJogador;
	}

	@Override
	public Jogador alterarJogador(Long id, JogadorDTO jogadorDTO) {
		Jogador jogador = buscarJogadorPorId(id);
		Time time = timeRepository.getOne(jogadorDTO.getId());
		if (jogador != null && time != null) {
			jogador.setTime(time);
			jogadorRepository.save(jogador);
			return jogador;
		}
		return jogador;
	}

	@Override
	public List<Jogador> buscarTodosJogadores() {
		return jogadorRepository.findAll();
	}

	@Override
	public boolean deletarJogadorPorId(Long id) {
		Optional<Jogador> jogador = jogadorRepository.findById(id);
		if (jogador.isPresent()) {
			jogadorRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
