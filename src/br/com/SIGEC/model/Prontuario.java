package br.com.SIGEC.model;

public class Prontuario {
	private double peso;
	private double altura;
	private int id;
	private String alergia;
	private String queixa;
	private double temperatura;
	private Medico medico;
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	private Paciente paciente;
	
	public Prontuario() {
		this.medico = new Medico();
		this.paciente = new Paciente();
	}
	
	public Prontuario(Double peso, Double altura, int id, String alergia, String queixa, double temperatura) {
		super();
		this.medico = new Medico();
		this.paciente = new Paciente();
		this.peso = peso;
		this.altura = altura;
		this.id = id;
		this.alergia = alergia;
		this.queixa = queixa;
		this.temperatura = temperatura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	
	
}
