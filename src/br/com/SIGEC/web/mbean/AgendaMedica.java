package br.com.SIGEC.web.mbean;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.model.Medico;

/**
 * 
 * @author Pierre Brito
 *
 *Essa classe é só para iniciar o componente de agenda do Primefaces
 */
@ManagedBean
public class AgendaMedica implements Serializable {
	private static final long serialVersionUID = 1L;
	private ScheduleModel schedule;
	
	@PostConstruct
	public void construir() {
		this.schedule = new DefaultScheduleModel();
	    
		Medico medico = new Medico();
		medico.setId(1);
		carregarConsultasNaAgenda(medico);
	}
	
	private void  carregarConsultasNaAgenda(Medico medico) {	
		List<br.com.SIGEC.model.Consulta> consultas = MedicoDAO.listarConsultas(medico);
		System.out.println(consultas.size());
		
		for (br.com.SIGEC.model.Consulta consulta : consultas) {
			adicionarConsulta(consulta);
		}	

	}
	
	private void adicionarConsulta(br.com.SIGEC.model.Consulta consulta) {
		DefaultScheduleEvent eventoConsulta = new DefaultScheduleEvent();
		eventoConsulta.setTitle(consulta.getPaciente().getPessoa().getNomeCompleto());
		eventoConsulta.setDescription("Consulta marcada com paciente de CPF " + consulta.getPaciente().getPessoa().getCpf());
		eventoConsulta.setStartDate(consulta.getHorario());
		eventoConsulta.setEndDate(fimDeConsulta(consulta.getHorario()));
		eventoConsulta.setUrl("editar_consulta.jsf?cons=" + consulta.getId());
		eventoConsulta.setEditable(false);//Ninguém move na agenda
		
		this.schedule.addEvent(eventoConsulta);
	}
	
	//O fim da consulta é 1hroa depois
	private Date fimDeConsulta(Date horarioDaConsulta) {
		LocalDateTime ldt = Instant.ofEpochMilli(horarioDaConsulta.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime()
			      .plusHours(1);
		
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

	public ScheduleModel getSchedule() {
		return schedule;
	}
	
}
