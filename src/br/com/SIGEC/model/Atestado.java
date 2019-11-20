package br.com.SIGEC.model;

import java.util.Date;

public class Atestado {
	
	private Paciente paciente;
	private Medico medico;
	private Date dataEmissao;
	private Date dataVencimento;
	private String cid;
	private int id;
	
	public Atestado() {
		super();
	}
	
	public Atestado(Paciente paciente, Medico medico, Date dataEmissao, Date dataVencimento, String cid, int id) {
		super();
		this.paciente = paciente;
		this.medico = medico;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.cid = cid;
		this.id = id;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
