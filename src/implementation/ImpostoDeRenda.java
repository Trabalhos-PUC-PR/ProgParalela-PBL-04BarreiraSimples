package implementation;

import entities.Funcionario;
import interfaces.Tax;

public class ImpostoDeRenda implements Tax{

	@Override
	public double getValorDesconto() {
		return 0.2;
	}

	@Override
	public void setDesconto(Funcionario f, double valor) {
		f.setDescontoImpostoDeRenda(valor);
	}
}
