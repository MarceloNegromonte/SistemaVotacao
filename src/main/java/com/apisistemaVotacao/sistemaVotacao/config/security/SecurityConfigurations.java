package com.apisistemaVotacao.sistemaVotacao.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apisistemaVotacao.sistemaVotacao.model.Usuario;
import com.apisistemaVotacao.sistemaVotacao.model.enums.TipoEnum;
import com.apisistemaVotacao.sistemaVotacao.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {

    String ROLE_ADMIN = "ADMIN";
    String ROLE_COOPERATE = "COOPERATE";
	
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired 
    public SecurityConfigurations(UsuarioRepository usuarioRepository, TokenService tokenService) {
    	this.usuarioRepository = usuarioRepository;
    	this.tokenService = tokenService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails usuario = Usuario.builder()
                .email("admin")
                .senha(encoder().encode("admin"))
                .tipo(TipoEnum.ADMIN).build();

        return new InMemoryUserDetailsManager(usuario);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/v1/pauta/*").permitAll()
        .antMatchers(HttpMethod.POST,"/v1/pauta/criarPauta").hasRole("ADMIN")
        
        .antMatchers(HttpMethod.GET, "/v1/sessao/*").permitAll()
        .antMatchers(HttpMethod.POST,"/v1/sessao/*").hasRole("ADMIN")
        
        .antMatchers(HttpMethod.POST, "/v1/voto/voto").permitAll()
        
        .antMatchers(HttpMethod.POST, "/v1/auth/login").permitAll()
        
        .antMatchers(HttpMethod.POST, "/v1/usuario/criar").permitAll()
        .antMatchers(HttpMethod.GET, "/v1/usuario/*").hasRole("ADMIN")

        .anyRequest().authenticated()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->  web.ignoring()
                .antMatchers("/**.html",
                        "/v2/api-docs/**",
                        "/webjars/**",
                        "/configuration/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**");   }
}
