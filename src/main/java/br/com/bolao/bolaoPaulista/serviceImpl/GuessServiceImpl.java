package br.com.bolao.bolaoPaulista.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.bolaoPaulista.dto.GuessDTO;
import br.com.bolao.bolaoPaulista.model.Guess;
import br.com.bolao.bolaoPaulista.model.Match;
import br.com.bolao.bolaoPaulista.model.Player;
import br.com.bolao.bolaoPaulista.model.Team;
import br.com.bolao.bolaoPaulista.repository.GuessRepository;
import br.com.bolao.bolaoPaulista.repository.PlayerRepository;
import br.com.bolao.bolaoPaulista.repository.TeamRepository;
import br.com.bolao.bolaoPaulista.service.GuessService;
import br.com.bolao.bolaoPaulista.service.MatchService;

@Service
public class GuessServiceImpl implements GuessService {

	@Autowired
	private GuessRepository guessRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MatchService matchService;

	@Override
	public List<Guess> findAllGuess() {
		return guessRepository.findAll();
	}

	@Override
	public Guess findGuessById(Long id) {
		Guess returnGuess = null;
		Optional<Guess> guess = guessRepository.findById(id);
		if (guess.isPresent()) {
			returnGuess = guess.get();
			return returnGuess;
		}
		return returnGuess;
	}

	@Override
	public Guess createGuess(GuessDTO guessDTO) {
		Guess guess = validGuessDTO(guessDTO);
		if (guess != null) {
			guessRepository.save(guess);
		}
		return guess;
	}

	private Guess validGuessDTO(GuessDTO guessDTO) {
		Guess guess = null;
		Player player = playerRepository.findByName(guessDTO.getPlayerName());
		Team homeTeam = teamRepository.findByName(guessDTO.getHomeTeam());
		Team awayTeam = teamRepository.findByName(guessDTO.getAwayTeam());
		if (player != null && homeTeam != null && awayTeam != null) {
			guess = new Guess(guessDTO.getId(), player, homeTeam, awayTeam, guessDTO.getGoalsHomeTeam(),
					guessDTO.getGoalsAwayTeam());
			addPonctuation(guess, player);
			return guess;
		}
		return guess;
	}

	private void addPonctuation(Guess guess, Player player) {
		int hitScore = 8;
		int hitWinner = 3;
		int hitWinnerGoalsTeam = 4;
		int hitGoalsTeam = 1;
		boolean winnerHomeTeam = false;
		boolean winnerAwayTeam = false;
		boolean draw = false;
		Team winnerTeam = null;

		Match match = matchService.findByTeams(guess);
		int matchGoalsHome = match.getGoalsHomeTeam();
		int matchGoalsAway = match.getGoalsAwayTeam();
		int guessGoalsHome = guess.getGoalsHomeTeam();
		int guessGoalsAway = guess.getGoalsAwayTeam();

		// se o time da casa venceu a partida
		if (matchGoalsHome > matchGoalsAway) {
			winnerHomeTeam = true;
			winnerTeam = guess.getHomeTeam();
		}
		// se o time visitante venceu a partida
		else if (matchGoalsHome < matchGoalsAway) {
			winnerAwayTeam = true;
			winnerTeam = guess.getAwayTeam();
		}
		// se a partida terminou empatada
		else {
			draw = true;
		}

		// acertou o palpite!!
		if (matchGoalsHome == guessGoalsHome && matchGoalsAway == guessGoalsAway) {
			System.out.println("Parabéns, você acertou o placar, você ganhou " + hitScore + " pontos");
			player.setScore(player.getScore() + hitScore);

		} // Não acertou o palpite, porém acertou a quantidade de gols do time da casa ou
			// visitante

		else if ((matchGoalsHome == guessGoalsHome && matchGoalsAway != guessGoalsAway)
				|| (matchGoalsHome != guessGoalsHome && matchGoalsAway == guessGoalsAway)) {

			if ((winnerHomeTeam && matchGoalsHome == guessGoalsHome && matchGoalsAway != guessGoalsAway)
					|| (winnerAwayTeam && matchGoalsAway == guessGoalsAway && matchGoalsHome != guessGoalsHome)
					|| (winnerHomeTeam && matchGoalsHome != guessGoalsHome && matchGoalsAway == guessGoalsAway)
					|| (winnerAwayTeam && matchGoalsAway != guessGoalsAway && matchGoalsHome == guessGoalsHome)) {

				// acertou que o time da casa venceu a partida e a quantidade de gols de um dos
				// times
				if (guessGoalsHome > guessGoalsAway && winnerHomeTeam) {
					System.out.println("Time da casa venceu");
					System.out.println(
							"Você acertou o palpite do time da casa que venceu e a quantidade de gols de um dos times, você ganhou "
									+ hitWinnerGoalsTeam + " pontos");
					player.setScore(player.getScore() + hitWinnerGoalsTeam);
				}
				// acertou que o time visitante venceu a partida e a quantidade de gols de um
				// dos times
				else if (guessGoalsHome < guessGoalsAway && winnerAwayTeam) {
					System.out.println("Time visitante venceu");
					System.out.println(
							"Você acertou o palpite do time visitante que venceu e a quantidade de gols de um dos times, você ganhou "
									+ hitWinnerGoalsTeam + " pontos");
					player.setScore(player.getScore() + hitWinnerGoalsTeam);
				}
				// errou que haveria um vencedor, mas acertou a quantidade de gols de um dos
				// times
				else {
					System.out.println("Você acertou a quantidade de gols de um dos times, você ganhou " + hitGoalsTeam
							+ " ponto");
					player.setScore(player.getScore() + hitGoalsTeam);
				}
			}

			else if (matchGoalsHome == guessGoalsHome || matchGoalsAway == guessGoalsAway)

			{
				System.out.println(
						"Você acertou a quantidade de gols de um dos times, você ganhou " + hitGoalsTeam + " ponto");
				player.setScore(player.getScore() + hitGoalsTeam);
			}
		} else if (matchGoalsHome != guessGoalsHome && matchGoalsAway != guessGoalsAway)

		{
			// melhorar daqui para baixo, quando o vencedor é o time visitante não está
			// funcionando
			if (winnerTeam.getName().equals(guess.getHomeTeam().getName())) {
				// mostra("Time da casa venceu");
				if (guessGoalsHome > guessGoalsAway) {
					System.out.println(
							"Você acertou quem venceu,  porém não acertou a quantidade de gols de nenhum dos times, você ganhou "
									+ hitWinner + " pontos");
					player.setScore(player.getScore() + hitWinner);
				} else if (guessGoalsHome < guessGoalsAway) {
					System.out.println("Você foi perna de pau, não ganhou nenhum ponto nessa partida");
				} else {
					System.out.println("Você foi perna de pau, não ganhou nenhum ponto nessa partida");
				}
			} else if (winnerTeam.getName().equals(guess.getAwayTeam().getName())) {
				// mostra("Time visitante venceu");
				if (guessGoalsHome < guessGoalsAway) {
					System.out.println(
							"Você acertou quem venceu,  porém não acertou a quantidade de gols de nenhum dos times, você ganhou "
									+ hitWinner + " pontos");
					player.setScore(player.getScore() + hitWinner);
				} else if (guessGoalsHome > guessGoalsAway) {
					System.out.println("Você foi perna de pau, não ganhou nenhum ponto nessa partida");
				} else {
					System.out.println("Você foi perna de pau, não ganhou nenhum ponto nessa partida");
				}
			}

		} else if (draw) {
			// errou que era empate, mas acertou a quantidade de gols de um dos times
			if ((guessGoalsHome != guessGoalsAway)
					&& (matchGoalsHome == guessGoalsHome || matchGoalsAway == guessGoalsAway)) {
				System.out.println(
						"Você não acertou que a partida terminou empatada, mas acertou a quantidade de gols de um dos times, você ganhou "
								+ hitGoalsTeam + " ponto");
				player.setScore(player.getScore() + hitGoalsTeam);
			}
			// errou tudo
			else if (guessGoalsHome != guessGoalsAway) {
				System.out.println("Você foi perna de pau, não ganhou nenhum ponto nessa partida");
			}
			// acertou que era empate, mas errou a quantidade de gols dos dois times
			else {
				System.out.println(
						"Você acertou que essa partida terminaria empatada, porém não acertou a quantidade de gols de nenhum dos times, você ganhou "
								+ hitWinner + " pontos");
				player.setScore(player.getScore() + hitWinner);
			}
		} else {
			System.out.println("Você foi perna de pau, não ganhou nenhum ponto nessa partida");
		}
		playerRepository.save(player);
	}

	@Override
	public Guess updateGuess(GuessDTO guessDTO, Long id) {
		Guess guess = null;
		// analisar como buscar player pelo id
		Player player = playerRepository.findByName(guessDTO.getPlayerName());
		Team homeTeam = teamRepository.findByName(guessDTO.getHomeTeam());
		Team awayTeam = teamRepository.findByName(guessDTO.getAwayTeam());
		if (player != null && homeTeam != null && awayTeam != null) {
			guess = guessRepository.getOne(id);
			guess.setPlayer(player);
			guess.setHomeTeam(homeTeam);
			guess.setAwayTeam(awayTeam);
			guess.setGoalsHomeTeam(guessDTO.getGoalsHomeTeam());
			guess.setGoalsAwayTeam(guessDTO.getGoalsAwayTeam());
			guessRepository.save(guess);
			return guess;
		}

		return guess;
	}

	@Override
	public boolean removeGuessById(Long id) {
		Optional<Guess> guess = guessRepository.findById(id);
		if (guess.isPresent()) {
			guessRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
