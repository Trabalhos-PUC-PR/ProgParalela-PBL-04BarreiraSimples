package interfaces;

import entities.Funcionario;

public interface Tax {

	/**
	 * Devolve o valor do desconto em decimal
	 * @return 
	 */
	public double getValorDesconto();
	
	/**
	 * Seta desconto da implementação
	 * @param f funcionario
	 */
	public void setDesconto(Funcionario f, double valor);
	
}
