package br.com.SIGEC.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.VerConsultasDAO;
import br.com.SIGEC.model.VerConsultas;

@ManagedBean
@ViewScoped
public class MinhasConsultasMB extends AbstractMBean{
	
	
		private List<VerConsultas> minhasConsultas = new ArrayList<>();
		
		@PostConstruct
		public void init() {
			VerConsultasDAO verConsultasDAO = new VerConsultasDAO();
			this.minhasConsultas = verConsultasDAO.buscarConsultaPorCPF("705.960.664-31");
		}

		public List<VerConsultas> getMinhasConsultas() {
			return this.minhasConsultas;
		}
		
	}



