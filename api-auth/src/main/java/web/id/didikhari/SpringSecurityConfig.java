package web.id.didikhari;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
		http.csrf().disable().authorizeRequests().antMatchers("/user").hasRole("USER").and().httpBasic();
		http.csrf().disable().authorizeRequests().antMatchers("/admin").hasRole("ADMIN").and().httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("admin@gmail.com").password("admin123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user@gmail.com").password("user123").roles("USER");
	}
	
	public AccessDeniedHandler getAccessDeniedHandler(){
		AccessDeniedHandler adh = new AccessDeniedHandler() {
			
			@Override
			public void handle(HttpServletRequest request,
					HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException,
					ServletException {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print("{" +
						"    \"message\": \"Akses Ditolak\"," +
						"    \"status\": \"failed\"" +
						"}");
				out.flush();
			}
		};
		return adh;
	}
}
