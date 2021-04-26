package br.com.bolao.bolaoPaulista.controller;

import java.net.URI;
import java.util.List;

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

import br.com.bolao.bolaoPaulista.dto.PalpiteDTO;
import br.com.bolao.bolaoPaulista.modelo.Palpite;
import br.com.bolao.bolaoPaulista.service.PalpiteService;

@RestController
@RequestMapping("/palpite")
public class PalpiteController {

	@Autowired
	private PalpiteService palpiteService;

	@GetMapping
	private List<PalpiteDTO> listar() {
		List<Palpite> palpites = palpiteService.buscarTodosPalpites();
		return PalpiteDTO.converterPalpite(palpites);
	}

	@GetMapping("/{id}")
	private ResponseEntity<PalpiteDTO> buscarPorId(@PathVariable Long id) {
		Palpite palpite = palpiteService.buscarPorId(id);
		if (palpite != null) {
			return ResponseEntity.ok(new PalpiteDTO(palpite));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	private ResponseEntity<PalpiteDTO> cadastrar(@RequestBody PalpiteDTO palpiteDTO, UriComponentsBuilder uriBuilder) {
		Palpite palpite = palpiteService.cadastrar(palpiteDTO);
		if (palpite != null) {
			URI uri = uriBuilder.path("/palpite/{id}").buildAndExpand(palpite.getId()).toUri();
			return ResponseEntity.created(uri).body(new PalpiteDTO(palpite));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	private ResponseEntity<PalpiteDTO> alterar(@PathVariable Long id, @RequestBody PalpiteDTO palpiteDTO) {
		Palpite palpite = palpiteService.alterar(palpiteDTO, id);
		if (palpite != null) {
			return ResponseEntity.ok(new PalpiteDTO(palpite));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deletar(@PathVariable Long id) {
		boolean palpitou = palpiteService.deletar(id);
		if (palpitou) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
