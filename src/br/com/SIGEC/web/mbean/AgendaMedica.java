package br.com.SIGEC.web.mbean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Pessoa;

/**
 * 
 * @author Pierre Brito
 *
 *Essa classe é só para iniciar o componente de agenda do Primefaces
 */
@ManagedBean
@ViewScoped
public class AgendaMedica {
	private ScheduleModel eventModel;
	
	@PostConstruct
	public void init() {
		this.eventModel = new DefaultScheduleModel();
		
		DefaultScheduleEvent consulta = new DefaultScheduleEvent("Exame do toque", hoje1DaTarde(), hoje2DaTarde());
		consulta.setDescription("Vá com calma com paciente, pois já está com 80 anos.");
		this.eventModel.addEvent(consulta);
		
		Medico medico = new Medico();
		medico.setId(1);
		Pessoa pessoa = new Pessoa();
		pessoa.setNomeCompleto("Pierre Carlos de Brito");
		medico.setPessoa(pessoa);
		
		carregarConsultasNaAgenda(medico);
		
	}
	
	private void  carregarConsultasNaAgenda(Medico medico) {
		List<DefaultScheduleEvent> eventosConsultas = new ArrayList<>();
		
		List<Consulta> consultas = MedicoDAO.listarConsultas(medico);
		consultas.forEach(c -> {
			DefaultScheduleEvent evento = new DefaultScheduleEvent("Consulta com " + c.getPaciente(), c.getDataConsulta(), ultimaHoraDeHoje());
			evento.setDescription("Consulta marcada com o paciente de CPF...");
			evento.setAllDay(true);
			eventosConsultas.add(evento);
		});
		
		eventosConsultas.forEach(e -> this.eventModel.addEvent(e));

	}
	
	public void eventoSelecionado(SelectEvent evento) {
		DefaultScheduleEvent consulta = (DefaultScheduleEvent) evento.getSource();
		System.out.println(consulta.getTitle());
		System.out.println("EPAA");
    }
	
	public void eventoMovido(ScheduleEntryMoveEvent evento) {
		DefaultScheduleEvent consulta = (DefaultScheduleEvent) evento.getSource();
		System.out.println(consulta.getTitle());
		System.out.println("EPAA");
    }
    
	
	private Date ultimaHoraDeHoje() {
        LocalDateTime ldt = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
	
	private Date hoje1DaTarde() {
        LocalDateTime ldt = LocalDateTime.now().withHour(13).withMinute(0).withSecond(0).withNano(0);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
	
	
	private Date hoje2DaTarde() {
        LocalDateTime ldt = LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
		
}
