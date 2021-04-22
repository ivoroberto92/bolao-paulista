package br.com.bolao.bolaoPaulista.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bolao.bolaoPaulista.modelo.Partida;

public class PartidaDTO {

	private Long id;

	private String timeVisitante;

	private String timeCasa;

	private int golsCasa;

	private int golsVisitante;

	public PartidaDTO() {
	}

	public PartidaDTO(Partida partida) {
		this.id = partida.getId();
		this.timeVisitante = partida.getTimeVistitante().getNome();
		this.timeCasa = partida.getTimeCasa().getNome();
		this.golsCasa = partida.getGolsCasa();
		this.golsVisitante = partida.getGolsVisitante();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(String timeVisitante) {
		this.timeVisitante = timeVisitante;
	}

	public String getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(String timeCasa) {
		this.timeCasa = timeCasa;
	}

	public int getGolsCasa() {
		return golsCasa;
	}

	public void setGolsCasa(int golsCasa) {
		this.golsCasa = golsCasa;
	}

	public int getGolsVisitante() {
		return golsVisitante;
	}

	public void setGolsVisitante(int golsVisitante) {
		this.golsVisitante = golsVisitante;
	}

	public static List<PartidaDTO> converterParaDTO(List<Partida> partidas) {
		return partidas.stream().map(PartidaDTO::new).collect(Collectors.toList());
	}
}
