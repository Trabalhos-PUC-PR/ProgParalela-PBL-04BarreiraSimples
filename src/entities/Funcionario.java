package entities;

public class Funcionario {

	private int codigo;
	private double salarioBruto;

	private double descontoImpostoDeRenda;
	private double descontoINSS;
	private double descontoPrevidenciaPrivada;
	private double descontoPlanoDeSaude;
	private double totalDescontos;

	private double salarioLiquido;

	public Funcionario(int codigo, double salarioBruto) {
		this.codigo = codigo;
		this.salarioBruto = salarioBruto;
		descontoImpostoDeRenda = 0;
		descontoINSS = 0;
		descontoPrevidenciaPrivada = 0;
		descontoPlanoDeSaude = 0;
		totalDescontos = 0;
		salarioLiquido = 0;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setDescontoImpostoDeRenda(double descontoImpostoDeRenda) {
		this.descontoImpostoDeRenda = descontoImpostoDeRenda;
	}

	public void setDescontoINSS(double descontoINSS) {
		this.descontoINSS = descontoINSS;
	}

	public void setDescontoPrevidenciaPrivada(double descontoPrevidenciaPrivada) {
		this.descontoPrevidenciaPrivada = descontoPrevidenciaPrivada;
	}

	public void setDescontoPlanoDeSaude(double descontoPlanoDeSaude) {
		this.descontoPlanoDeSaude = descontoPlanoDeSaude;
	}

	private void updateSalarioLiquido() {
		salarioLiquido = salarioBruto - totalDescontos;
	}

	public void updateDescontos() {
		totalDescontos = descontoImpostoDeRenda + descontoINSS + descontoPrevidenciaPrivada + descontoPlanoDeSaude;
		updateSalarioLiquido();
	}

	@Override
	public String toString() {
		return "#%04d, sB=%.2f, sL=%7.2f ImpR=%7.02f, INSS=%6.2f, PrevPriv=%6.2f, pds=%6.2f".formatted(codigo,
				salarioBruto, salarioLiquido, descontoImpostoDeRenda, descontoINSS, descontoPrevidenciaPrivada,
				descontoPlanoDeSaude);
	}

}
