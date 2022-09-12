package implementation;

import entities.Funcionario;
import interfaces.Tax;

public class PrevidenciaPrivada implements Tax{

	@Override
	public double getValorDesconto() {
		return 0.04;
	}

	@Override
	public void setDesconto(Funcionario f, double valor) {
		f.setDescontoPrevidenciaPrivada(valor);
	}

}
