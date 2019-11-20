package br.com.SIGEC.web.mbean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

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
