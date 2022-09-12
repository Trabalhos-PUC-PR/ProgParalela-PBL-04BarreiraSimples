package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import entities.Funcionario;
import entities.TaxCalculator;
import implementation.INSS;
import implementation.ImpostoDeRenda;
import implementation.PlanoDeSaude;
import implementation.PrevidenciaPrivada;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Funcionario> lista = new ArrayList<Funcionario>();
		System.out.println(
				"Digite a quantidade de funcionarios: \n(deve ser multiplo de 4, senão será MULTIPLICADO por 4)");
		int quantity = 4;
		try {
			quantity = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Dados invalidos recebidos, valor padrão (4) selecionado");
		}

		if (quantity <= 0) {
			quantity = 4;
		}
		if (quantity % 4 != 0) {
			System.out.println("Valor não multiplo de 4, multiplicando por 4...");
			quantity *= 4;
		}

		for (int i = 0; i < quantity; i++) {
			Random rng = new Random();
			double salario = rng.nextDouble(4000) + 1000;
			lista.add(new Funcionario(i, salario));
		}

		int chunk = lista.size() / 4;
		List<List<Funcionario>> partes = new ArrayList<List<Funcionario>>();
		partes.add(lista.subList(chunk * 0, chunk * 1));
		partes.add(lista.subList(chunk * 1, chunk * 2));
		partes.add(lista.subList(chunk * 2, chunk * 3));
		partes.add(lista.subList(chunk * 3, chunk * 4));

		List<Semaphore> semaforos = new ArrayList<Semaphore>();
		semaforos.add(new Semaphore(0));
		semaforos.add(new Semaphore(0));
		semaforos.add(new Semaphore(0));
		semaforos.add(new Semaphore(0));

		TaxCalculator ImpostoDeRenda = new TaxCalculator(1, partes, semaforos, 0, new ImpostoDeRenda());
		TaxCalculator INSS = new TaxCalculator(2, partes, semaforos, 1, new INSS());
		TaxCalculator PrevidenciaPrivada = new TaxCalculator(3, partes, semaforos, 2, new PrevidenciaPrivada());
		TaxCalculator PlanoSaude = new TaxCalculator(4, partes, semaforos, 3, new PlanoDeSaude());

		long startTime = System.currentTimeMillis();
		
		ImpostoDeRenda.start();
		INSS.start();
		PrevidenciaPrivada.start();
		PlanoSaude.start();

		try {
			ImpostoDeRenda.join();
			INSS.join();
			PrevidenciaPrivada.join();
			PlanoSaude.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();

		int ms = (int)(endTime - startTime);
		int segundos = ms/1000;
		int minutos = segundos/60;
		ms = ms % 1000;
		segundos = segundos % 60;
		System.out.printf("Tempo decorrido: %02d:%02d.%03d", minutos, segundos, ms);
		
		sc.close();
	}

}
