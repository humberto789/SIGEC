package br.com.SIGEC.web.mbean;

import java.sql.Date;

public class Consulta {
private String especialidade;
private Date dataConsulta;
private String medico;
private String paciente;

public String getEspecialidade() {
	return especialidade;
}
public void setEspecialidade(String especialidade) {
	this.especialidade = especialidade;
}
public Date getDataConsulta() {
	return dataConsulta;
}
public void setDataConsulta(Date dataConsulta) {
	this.dataConsulta = dataConsulta;
}
public String getMedico() {
	return medico;
}
public void setMedico(String medico) {
	this.medico = medico;
}
public String getPaciente() {
	return paciente;
}
public void setPaciente(String paciente) {
	this.paciente = paciente;
}



}
