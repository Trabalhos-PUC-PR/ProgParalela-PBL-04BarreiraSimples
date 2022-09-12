package implementation;

import entities.Funcionario;
import interfaces.Tax;

public class PlanoDeSaude implements Tax{

	@Override
	public double getValorDesconto() {
		return 0.02;
	}

	@Override
	public void setDesconto(Funcionario f, double valor) {
		f.setDescontoPlanoDeSaude(valor);
	}

}
