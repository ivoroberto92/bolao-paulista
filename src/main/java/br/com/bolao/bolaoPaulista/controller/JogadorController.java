package br.com.bolao.bolaoPaulista.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.bolao.bolaoPaulista.dto.JogadorDTO;
import br.com.bolao.bolaoPaulista.modelo.Jogador;
import br.com.bolao.bolaoPaulista.modelo.Time;
import br.com.bolao.bolaoPaulista.repository.JogadorRepository;
import br.com.bolao.bolaoPaulista.service.JogadorService;
import br.com.bolao.bolaoPaulista.service.TimeService;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

	@Autowired
	private JogadorRepository jogadorRepository;

	@Autowired
	private JogadorService jogadorService;

	private TimeService timeService;

	@GetMapping
	public List<JogadorDTO> listar() {
		List<Jogador> jogadores = jogadorRepository.findAll();
		return JogadorDTO.converterParaDTO(jogadores);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JogadorDTO> detalharPorJogador(@PathVariable Long id) {
		Jogador jogador = jogadorService.buscarJogadorPorId(id);
		if (jogador != null) {
			return ResponseEntity.ok(new JogadorDTO(jogador));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<JogadorDTO> cadastrar(@RequestBody JogadorDTO jogadorDTO, UriComponentsBuilder uriBuilder) {
		Jogador jogador = jogadorService.atribuirTimeAoJogador(jogadorDTO.getNomeTime(), jogadorDTO.getNome());
		jogadorRepository.save(jogador);
		URI uri = uriBuilder.path("/jogador/{id}").buildAndExpand(jogador.getId()).toUri();
		return ResponseEntity.created(uri).body(new JogadorDTO(jogador));
	}

	@PutMapping("/{id}")
	// @Transactional
	public ResponseEntity<JogadorDTO> alterar(@PathVariable Long id, @RequestBody JogadorDTO jogadorDTO) {
		Jogador jogador = jogadorService.buscarJogadorPorId(id);
		Time time = timeService.buscarTimePorId(jogadorDTO.getId());
		jogador.setTime(time);
		jogadorRepository.save(jogador);
		return ResponseEntity.ok(new JogadorDTO(jogador));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Jogador> optionalJogador = jogadorRepository.findById(id);
		if (optionalJogador.isPresent()) {
			jogadorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
