package br.com.bolao.bolaoPaulista.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.PartidaDTO;
import br.com.bolao.bolaoPaulista.modelo.Partida;
import br.com.bolao.bolaoPaulista.modelo.Time;
import br.com.bolao.bolaoPaulista.repository.PartidaRepository;
import br.com.bolao.bolaoPaulista.repository.TimeRepository;
import br.com.bolao.bolaoPaulista.service.PartidaService;

@Service
public class PartidaServiceImpl implements PartidaService {

	@Autowired
	private PartidaRepository partidaRepository;
	@Autowired
	private TimeRepository timeRepository;

	@Override
	public List<Partida> buscarTodasPartidas() {
		List<Partida> partidas = partidaRepository.findAll();
		return partidas;
	}

	@Override
	public Partida busparPartidaPorId(Long id) {
		Optional<Partida> partida = partidaRepository.findById(id);
		if (partida.isPresent()) {
			return partida.get();
		}
		return null;
	}

	@Override
	public Partida atribuirTimeNaPartida(PartidaDTO partidaDTO, String nomeTimeCasa, String nomeTimeVisitante) {
		if (!nomeTimeCasa.equals(nomeTimeVisitante)) {
			Time timeCasa = timeRepository.findByNome(nomeTimeCasa);
			Time timeVisitante = timeRepository.findByNome(nomeTimeVisitante);
			if (timeCasa != null && timeVisitante != null) {
				Partida partida = new Partida(partidaDTO.getId(), timeCasa, timeVisitante, 
											  partidaDTO.getGolsCasa(), partidaDTO.getGolsVisitante());
				partidaRepository.save(partida);
				return partida;
			}
		}

		return null;
	}

	@Override
	public Partida alterarPartida(Long id, PartidaDTO partidaDTO) {
		Partida partida = buscarPartidaPorId(id);
		Time timeCasa = timeRepository.findByNome(partidaDTO.getTimeCasa());
		Time timeVisitante = timeRepository.findByNome(partidaDTO.getTimeVisitante());
		if(partida != null) {
			partida.setTimeCasa(timeCasa);
			partida.setTimeVistitante(timeVisitante);
			partida.setGolsCasa(partidaDTO.getGolsCasa());
			partida.setGolsVisitante(partidaDTO.getGolsVisitante());
			partidaRepository.save(partida);
			return partida;
		}
		return null;
	}

	@Override
	public Partida buscarPartidaPorId(Long id) {
		return partidaRepository.getOne(id);
	}

	@Override
	public boolean deletarPartida(Long id) {
		Optional<Partida> partida = partidaRepository.findById(id);
		if(partida.isPresent()) {
			partidaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
