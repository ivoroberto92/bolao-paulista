package br.com.bolao.bolaoPaulista.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import br.com.bolao.bolaoPaulista.modelo.Jogador;
import br.com.bolao.bolaoPaulista.modelo.Time;
import br.com.bolao.bolaoPaulista.repository.JogadorRepository;
import br.com.bolao.bolaoPaulista.repository.TimeRepository;

public class JogadorDTO {
	
	private Long id;
	private String nome;
	private String nomeTime;
	private Long timeId;

	public JogadorDTO() {
	}
	public JogadorDTO(Jogador jogador) {
		this.id = jogador.getId();
		this.nome = jogador.getNome();
		this.nomeTime = jogador.getTime().getNome();
		this.timeId = jogador.getTime().getId();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	public Long getTimeId() {
		return timeId;
	}
	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	
	public static List<JogadorDTO> converterParaDTO(List<Jogador> jogadores) {
		return jogadores.stream().map(JogadorDTO::new).collect(Collectors.toList());
	}
	
}
