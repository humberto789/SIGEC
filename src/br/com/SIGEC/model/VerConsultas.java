package br.com.SIGEC.model;

import java.util.Date;



public class VerConsultas {
	private Paciente paciente;
	private Medico medico;
	private Date dataConsulta;
	private String especialidade;
	private int id;
	
	
	public VerConsultas (){ 
		super();
		this.paciente = new Paciente();
		this.medico = new Medico();
		this.dataConsulta = new Date();
	
	}
	
	public VerConsultas(Paciente paciente, Medico medico, Date dataConsulta, int id) {
		this();
		this.paciente = paciente;
		this.medico = medico;
		this.dataConsulta = dataConsulta;
		this.id = id;
	
	

	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public Date getDataConsulta() {
		return dataConsulta;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void add(VerConsultas consulta) {
		// TODO Auto-generated method stub
		
	}

	public void setDataConsulta(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void setPaciente(Paciente paciente2) {
		// TODO Auto-generated method stub
		
	}

	public void setMedico(Medico medico2) {
		// TODO Auto-generated method stub
		
	}

		
	}




