package br.com.bolao.bolaoPaulista.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bolao.bolaoPaulista.dto.PlayerDTO;
import br.com.bolao.bolaoPaulista.model.Player;
import br.com.bolao.bolaoPaulista.service.PlayerService;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "*")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping
	public List<PlayerDTO> listAll() {
		List<Player> playeres = playerService.findAllPlayers();
		return PlayerDTO.converterParaDTO(playeres);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlayerDTO> findById(@PathVariable Long id) {
		Player player = playerService.findPlayerById(id);
		if (player != null) {
			return ResponseEntity.ok(new PlayerDTO(player));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<PlayerDTO> create(@RequestBody PlayerDTO playerDTO, UriComponentsBuilder uriBuilder) {
		Player player = playerService.createPlayer(playerDTO);
		URI uri = uriBuilder.path("/players/{id}").buildAndExpand(player.getId()).toUri();
		System.out.println(uri);
		return ResponseEntity.created(uri).body(new PlayerDTO(player));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
		Player player = playerService.updatePlayer(id, playerDTO);
		return ResponseEntity.ok(new PlayerDTO(player));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		boolean excluded = playerService.removePlayerById(id);
		if (excluded) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
