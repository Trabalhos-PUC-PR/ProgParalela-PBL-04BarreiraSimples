package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;
import interfaces.Tax;

public class TaxCalculator extends Thread {

	private int id;
	private int parteInicial;
	private int parteFinal;
	private Tax imposto;
	private List<List<Funcionario>> funcionarios;
	private List<Semaphore> semaforos;

	public TaxCalculator(int id, List<List<Funcionario>> funcionarios, List<Semaphore> semaforos, int parteInicial,
			Tax imposto) {
		this.id = id;
		this.semaforos = semaforos;
		this.funcionarios = funcionarios;
		this.parteInicial = parteInicial;
		this.parteFinal = parteInicial + funcionarios.size() - 1;
		this.imposto = imposto;
	}

	public void impressao(List<List<Funcionario>> funcionarios) {
		try {
			File file = new File("parte" + id + ".txt");
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (Funcionario f : funcionarios.get(parteInicial)) {
				bw.append(f.toString() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double calculo(Funcionario funcionario) {
		return funcionario.getSalarioBruto() * imposto.getValorDesconto();
	}

	public void run() {
		int atual = parteInicial;
		while (atual <= parteFinal) {
			for (Funcionario f : funcionarios.get(atual % funcionarios.size())) {
				imposto.setDesconto(f, calculo(f));
				f.updateDescontos();
			}
			atual++;
		}
		System.out.printf("%d OK\n", id);
		rendezvous();
		impressao(funcionarios);
	}

	public void rendezvous() {
		semaforos.get(parteInicial).release(semaforos.size());
		for (Semaphore s : semaforos) {
			try {
				s.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "TaxCalculator [id=" + id + ", parteInicial=" + parteInicial + ", parteFinal=" + parteFinal
				+ ", porcentagem=" + imposto + ", funcionarios=" + funcionarios + ", semaforos=" + semaforos + "]";
	}

}
