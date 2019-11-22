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
	public class minhasConsultasMbean extends AbstractMBean {
		
		private List<VerConsultas> minhasConsultas = new ArrayList<>();
		
		@PostConstruct
		public void init() {
			VerConsultasDAO VerConsultasDAO = new VerConsultasDAO();
			this.minhasConsultas = VerConsultasDAO.buscarVerConsultasPorCPF();
		}

		public List<VerConsultas> getminhasConsultas() {
			return this.minhasConsultas;
		}

		public List<VerConsultas> getMinhasConsultas() {
			return minhasConsultas;
		}

		public void setMinhasConsultas(List<VerConsultas> minhasConsultas) {
			this.minhasConsultas = minhasConsultas;
		}
		
	}

