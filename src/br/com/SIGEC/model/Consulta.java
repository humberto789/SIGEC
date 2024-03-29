package br.com.SIGEC.model;

import java.util.Date;

public class Consulta {
	private Medico medico;
	private Paciente paciente;
	private Date horario;
	private int id;
	private boolean realizada;
	
	public Consulta() {
		super();
		medico = new Medico();
		paciente = new Paciente();
	}
	
	public Consulta(Medico medico, Paciente paciente, Date horario, int id, boolean realizada) {
		super();
		this.medico = medico;
		this.paciente = paciente;
		this.horario = horario;
		this.id = id;
		this.realizada = realizada;
	}
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
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isRealizada() {
		return realizada;
	}
	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}
}
