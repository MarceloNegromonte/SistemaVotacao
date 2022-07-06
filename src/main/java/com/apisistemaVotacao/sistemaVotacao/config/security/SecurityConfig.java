package com.apisistemaVotacao.sistemaVotacao.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;*/

import com.apisistemaVotacao.sistemaVotacao.repository.UsuarioRepository;

/*@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	public String encriptyPassword (String password) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String passwordEncoder = enconder.encode(password);
		return passwordEncoder;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    .antMatchers(HttpMethod.GET, "/pauta").permitAll()
	    .antMatchers(HttpMethod.GET, "/pauta/*").permitAll()
	    .antMatchers(HttpMethod.POST, "/auth").permitAll()
	    .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
	    .anyRequest().authenticated()
	    .and().csrf().disable()
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }//corrigir o caminho passado
}*/
