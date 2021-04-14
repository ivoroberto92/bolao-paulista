package br.com.bolao.bolaoPaulista.service;

import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.modelo.Time;

public interface TimeService {

	Time buscarTimePorId(Long id);

}
