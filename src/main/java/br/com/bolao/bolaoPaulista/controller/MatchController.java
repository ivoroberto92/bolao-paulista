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

import br.com.bolao.bolaoPaulista.dto.MatchDTO;
import br.com.bolao.bolaoPaulista.model.Match;
import br.com.bolao.bolaoPaulista.model.Team;
import br.com.bolao.bolaoPaulista.service.MatchService;

@RestController
@RequestMapping("/matches")
@CrossOrigin(origins = "*")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@GetMapping
	private List<MatchDTO> listAll() {
		List<Match> matches = matchService.findAllMatches();
		return MatchDTO.converterParaDTO(matches);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<MatchDTO> findMatchById(@PathVariable Long id, MatchDTO matchDTO) {
		Match match = matchService.findMatchById(id);
		if (match != null) {
			return ResponseEntity.ok(new MatchDTO(match));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping()
	private ResponseEntity<MatchDTO> create(@RequestBody MatchDTO matchDTO, UriComponentsBuilder uriBuilder) {
		Match match = matchService.createMatch(matchDTO);
		if (match != null) {
			URI uri = uriBuilder.path("/matches/{id}").buildAndExpand(match.getId()).toUri();
			return ResponseEntity.created(uri).body(new MatchDTO(match));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	private ResponseEntity<MatchDTO> update(@PathVariable Long id, @RequestBody MatchDTO matchDTO) {
		Match match = matchService.updateMatch(id, matchDTO);
		if (match != null) {
			return ResponseEntity.ok(new MatchDTO(match));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	private ResponseEntity<MatchDTO> deleteById(@PathVariable Long id) {
		boolean excluded = matchService.removeMatchById(id);
		if (excluded) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}