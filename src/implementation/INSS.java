package implementation;

import entities.Funcionario;
import interfaces.Tax;

public class INSS implements Tax{

	@Override
	public double getValorDesconto() {
		return 0.08;
	}

	@Override
	public void setDesconto(Funcionario f, double valor) {
		f.setDescontoINSS(valor);
	}

}
