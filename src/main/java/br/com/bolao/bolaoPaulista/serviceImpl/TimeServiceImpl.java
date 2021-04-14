package br.com.bolao.bolaoPaulista.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.modelo.Time;
import br.com.bolao.bolaoPaulista.repository.TimeRepository;
import br.com.bolao.bolaoPaulista.service.TimeService;
@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeRepository timeRepository;
	
	@Override
	public Time buscarTimePorId(Long idTime) {
		Time time = timeRepository.getOne(idTime);
		if(time != null) {
			return time;
		}
//		Optional<Time> optionalTime = timeRepository.findById(idTime);
//		if (optionalTime.isPresent()) {
//			Time time = optionalTime.get();
//			return time;
//		}
		return null;
	}

}
