package br.com.SIGEC.web.mbean;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;




public class AgendarEmailJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// Enviar e-mail
		
		ConfirmarConsultaMB Confirmar = new ConfirmarConsultaMB();
			
		
		Confirmar.enviarEmail();
		

	}
}
