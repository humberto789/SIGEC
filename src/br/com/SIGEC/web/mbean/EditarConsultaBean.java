package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.ConsultaDAO;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Pessoa;

@ManagedBean
@ViewScoped
public class EditarConsultaBean {
	private br.com.SIGEC.model.Consulta consulta;
	private int id;
	
	public void carregarConsulta() {
		Medico medico = new Medico();
		Pessoa pessoa = new Pessoa();
		pessoa.setNomeCompleto("Pierre Carlos de Brito");
		medico.setCrm("893482");
		medico.setPessoa(pessoa);
		this.consulta = ConsultaDAO.buscar(this.id, medico);	
		System.out.println(this.consulta.getHorario());
	}
	
	public String cancelarConsulta() {
		ConsultaDAO.cancelarConsulta(this.consulta);
		return "agenda_medica?faces-redirect=true";
	}
	
	public String salvar() {
		ConsultaDAO.atualizar(this.consulta);
		return "agenda_medica?faces-redirect=true";
	}
	
	public br.com.SIGEC.model.Consulta getConsulta() {
		return consulta;
	}
	
	public void setConsulta(br.com.SIGEC.model.Consulta consulta) {
		this.consulta = consulta;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
