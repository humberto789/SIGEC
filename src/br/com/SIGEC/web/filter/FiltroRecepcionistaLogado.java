package br.com.SIGEC.web.filter;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.model.Usuario;

@WebFilter(urlPatterns={"/recepcionista/*"})
@ManagedBean
public class FiltroRecepcionistaLogado implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest .getSession(true); 
	    Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
	    
		if(usuario!=null) {
			if(usuario.getTipoUsuario().equals("recepcionista")) {
				chain.doFilter(request, response);		
			}else {
				 HttpServletResponse resp = (HttpServletResponse)response;
				resp.sendRedirect("/SIGEC/error/403.jsf");
			}
		}else {
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect("/SIGEC/login.jsf");
			return;
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}