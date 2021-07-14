package br.com.bolao.bolaoPaulista;

import java.text.DecimalFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class BolaoPaulistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BolaoPaulistaApplication.class, args);
//		DecimalFormat deci = new DecimalFormat("0.00");
//		int tempo = 52;
//		double aporteSemanal = 7.3;
//		double resultado = 0;
//		double[] valorSemanal = new double[tempo];
//		double resultadoFinal = 0;
//		for (int i = 0; i < tempo; i++) {
//			resultado += aporteSemanal;
//			valorSemanal[i] = resultado;
//			System.out.println("semana " + (i + 1) + " " + deci.format(resultado));
//			
//		}
//		for (int i = 0; i < valorSemanal.length; i++) {
//			resultadoFinal += valorSemanal[i];
//		}
//		System.out.println("valor final: " + Math.ceil(resultadoFinal));
	}

}
