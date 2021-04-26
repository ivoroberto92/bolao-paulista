package br.com.bolao.bolaoPaulista.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bolao.bolaoPaulista.modelo.Palpite;

public class PalpiteDTO {

	private Long id;

	//private Long idJogador;

	private String nomeJogador;

	private String timeVisitante;

	private String timeCasa;

	private int golsCasa;

	private int golsVisitante;

	public PalpiteDTO() {
	}

	public PalpiteDTO(Palpite palpite) {
		this.id = palpite.getId();
		//this.idJogador = palpite.getJogador().getId();
		this.nomeJogador = palpite.getJogador().getNome();
		this.timeVisitante = palpite.getTimeVisitante().getNome();
		this.timeCasa = palpite.getTimeCasa().getNome();
		this.golsCasa = palpite.getGolsCasa();
		this.golsVisitante = palpite.getGolsVisitante();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getIdJogador() {
//		return idJogador;
//	}
//
//	public void setIdJogador(Long idJogador) {
//		this.idJogador = idJogador;
//	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
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

	public static List<PalpiteDTO> converterPalpite(List<Palpite> palpites) {
		return palpites.stream().map(PalpiteDTO::new).collect(Collectors.toList());
	}

}
