package br.com.bolao.bolaoPaulista.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bolao.bolaoPaulista.dto.PartidaDTO;
import br.com.bolao.bolaoPaulista.modelo.Partida;
import br.com.bolao.bolaoPaulista.service.PartidaService;

@RestController
@RequestMapping("/partida")
public class PartidaController {

	@Autowired
	private PartidaService partidaService;

	@GetMapping
	private List<PartidaDTO> listar() {
		List<Partida> partidas = partidaService.buscarTodasPartidas();
		return PartidaDTO.converterParaDTO(partidas);

	}

	@GetMapping("/{id}")
	private ResponseEntity<PartidaDTO> detalhar(@PathVariable Long id, PartidaDTO partidaDTO) {
		Partida partida = partidaService.buscarPartidaPorId(id);
		if (partida != null) {
			return ResponseEntity.ok(new PartidaDTO(partida));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping()
	private ResponseEntity<PartidaDTO> cadastrar(@RequestBody PartidaDTO partidaDTO, UriComponentsBuilder uriBuilder) {
		Partida partida = partidaService.atribuirTimeNaPartida(partidaDTO, 
															   partidaDTO.getTimeCasa(),
															   partidaDTO.getTimeVisitante());
		if (partida != null) {
			URI uri = uriBuilder.path("/partida/{id}").buildAndExpand(partida.getId()).toUri();
			return ResponseEntity.created(uri).body(new PartidaDTO(partida));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	private ResponseEntity<PartidaDTO> alterar(@PathVariable Long id, @RequestBody PartidaDTO partidaDTO) {
		Partida partida = partidaService.alterarPartida(id, partidaDTO);
		if (partida != null) {
			return ResponseEntity.ok(new PartidaDTO(partida));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	private ResponseEntity<PartidaDTO> deletar(@PathVariable Long id) {
		boolean partida = partidaService.deletarPartida(id);
		if (partida) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}