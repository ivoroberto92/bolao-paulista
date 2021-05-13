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

import br.com.bolao.bolaoPaulista.dto.GuessDTO;
import br.com.bolao.bolaoPaulista.model.Guess;
import br.com.bolao.bolaoPaulista.service.GuessService;

@RestController
@RequestMapping("/guesses")
public class GuessController {

	@Autowired
	private GuessService guessService;

	@GetMapping
	private List<GuessDTO> listAll() {
		List<Guess> guesss = guessService.findAllGuess();
		return GuessDTO.converterGuess(guesss);
	}

	@GetMapping("/{id}")
	private ResponseEntity<GuessDTO> findById(@PathVariable Long id) {
		Guess guess = guessService.findGuessById(id);
		if (guess != null) {
			return ResponseEntity.ok(new GuessDTO(guess));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	private ResponseEntity<?> create(@RequestBody GuessDTO guessDTO, UriComponentsBuilder uriBuilder) {
		Guess guess = guessService.createGuess(guessDTO);		
		if (guess != null) {
			URI uri = uriBuilder.path("/guesses/{id}").buildAndExpand(guess.getId()).toUri();
			return ResponseEntity.created(uri).body(new GuessDTO(guess));
		}
		ErrorSintaxe error = new ErrorSintaxe("Não foi possível realizar o cadastro devido erro nas informações");
		return ResponseEntity.badRequest().body(error.getMessage());
	}

	@PutMapping("/{id}")
	private ResponseEntity<GuessDTO> update(@PathVariable Long id, @RequestBody GuessDTO guessDTO) {
		Guess guess = guessService.updateGuess(guessDTO, id);
		if (guess != null) {
			return ResponseEntity.ok(new GuessDTO(guess));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteById(@PathVariable Long id) {
		boolean excluded = guessService.removeGuessById(id);
		if (excluded) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
