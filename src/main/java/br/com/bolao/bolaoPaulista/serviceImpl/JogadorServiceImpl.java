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
		Optional<Jogador> optionalJogador = jogadorRepository.findById(id);
		if (optionalJogador.isPresent()) {
			Jogador jogador = optionalJogador.get();
			return jogador;
		}
		return null;
	}

	@Override
	public Jogador cadastrarJogador(String nomeTime, String nomeJogador) {
		Time time = timeRepository.findByNome(nomeTime);
		if (time != null) {
			Jogador jogador = new Jogador(nomeJogador, time);
			jogadorRepository.save(jogador);
			return jogador;
		}
		return null;
	}

	@Override
	public Optional<Jogador> findById(Long id) {
		return jogadorRepository.findById(id);
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
		return null;
	}

	@Override
	public List<Jogador> buscarTodosJogadores() {
		List<Jogador> jogadores = jogadorRepository.findAll();
		return jogadores;
	}

	@Override
	public boolean deletarJogadorPorId(Long id) {
		Optional<Jogador> optionalJogador = jogadorRepository.findById(id);
		if (optionalJogador.isPresent()) {
			jogadorRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
