package br.com.bolao.bolaoPaulista.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.PalpiteDTO;
import br.com.bolao.bolaoPaulista.modelo.Jogador;
import br.com.bolao.bolaoPaulista.modelo.Palpite;
import br.com.bolao.bolaoPaulista.modelo.Time;
import br.com.bolao.bolaoPaulista.repository.JogadorRepository;
import br.com.bolao.bolaoPaulista.repository.PalpiteRepository;
import br.com.bolao.bolaoPaulista.repository.TimeRepository;
import br.com.bolao.bolaoPaulista.service.PalpiteService;
@Service
public class PalpiteServiceImpl implements PalpiteService {
	
	@Autowired
	private PalpiteRepository palpiteRepository;
	
	@Autowired
	private JogadorRepository jogadorRepository;
	@Autowired
	private TimeRepository timeRepository;
	
	@Override
	public List<Palpite> buscarTodosPalpites() {
		List<Palpite> palpites = palpiteRepository.findAll();
		return palpites;
	}

	@Override
	public Palpite buscarPorId(Long id) {
		Optional<Palpite> palpiteOptional = palpiteRepository.findById(id);
		if(palpiteOptional.isPresent()) {
			Palpite palpite = palpiteOptional.get();
			return palpite;
		}
		return null;
	}

	@Override
	public Palpite cadastrar(PalpiteDTO palpiteDTO) {
		Jogador jogador = jogadorRepository.findByNome(palpiteDTO.getNomeJogador());
		Time timeCasa = timeRepository.findByNome(palpiteDTO.getTimeCasa());
		Time timeVisitante = timeRepository.findByNome(palpiteDTO.getTimeVisitante());
		if(jogador != null && timeCasa != null && timeVisitante != null) {
		Palpite	palpite = new Palpite(palpiteDTO.getId(), jogador, timeCasa, 
							   timeVisitante, palpiteDTO.getGolsCasa(), 
							   palpiteDTO.getGolsVisitante());
			palpiteRepository.save(palpite); 
			return palpite;
		}
		return null;
	}

	@Override
	public Palpite alterar(PalpiteDTO palpiteDTO, Long id) {
		//analisar como buscar jogador pelo id
		Jogador jogador = jogadorRepository.findByNome(palpiteDTO.getNomeJogador());
		Time timeCasa = timeRepository.findByNome(palpiteDTO.getTimeCasa());
		Time timeVisitante = timeRepository.findByNome(palpiteDTO.getTimeVisitante());
		if(jogador != null && timeCasa != null && timeVisitante != null) {
			Palpite palpite = palpiteRepository.getOne(id);
			palpite.setJogador(jogador);
			palpite.setTimeCasa(timeCasa);
			palpite.setTimeVisitante(timeVisitante);
			palpite.setGolsCasa(palpiteDTO.getGolsCasa());
			palpite.setGolsVisitante(palpiteDTO.getGolsVisitante());
			palpiteRepository.save(palpite);
			return palpite;
		}
		
		return null;
	}

	@Override
	public boolean deletar(Long id) {
		Optional<Palpite> palpite = palpiteRepository.findById(id);
		if(palpite.isPresent()) {
			palpiteRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
