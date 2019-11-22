package br.com.SIGEC.web.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import br.com.SIGEC.web.mbean.AutenticarUsuarioMBean;

@WebFilter(urlPatterns={"/admin/*"})
public class FiltroUsuarioLogado implements Filter{
	
	@Inject
	private AutenticarUsuarioMBean autenticar;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(autenticar.getUsuario()==null) {
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect("/SIGEC/login.jsf");
			return;
		}
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
