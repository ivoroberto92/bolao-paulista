package br.com.bolao.bolaoPaulista.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity	
public class Partida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "timeVisitante_id")
	private Time timeVisitante;
	@ManyToOne
	@JoinColumn(name = "timeCasa_id")
	private Time timeCasa;
	
	private int golsCasa;
	
	private int golsVisitante;
	
	public Partida() {
	}
	
	public Partida(Long id, Time timeVistitante, Time timeCasa, int golsCasa, int golsVisitante) {
		this.id = id;
		this.timeVisitante = timeVistitante;
		this.timeCasa = timeCasa;
		this.golsCasa = golsCasa;
		this.golsVisitante = golsVisitante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTimeVistitante() {
		return timeVisitante;
	}

	public void setTimeVistitante(Time timeVistitante) {
		this.timeVisitante = timeVistitante;
	}

	public Time getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(Time timeCasa) {
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
}
