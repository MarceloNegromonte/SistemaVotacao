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
	
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails usuario = Usuario.builder()
                .email("admin")
                .senha(encoder().encode("admin"))
                .tipo(TipoEnum.ADMIN).build();

        return new InMemoryUserDetailsManager(usuario);
    }
    
	//configuracoes de autorizacao
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/v1/pauta/*").permitAll()
        .antMatchers(HttpMethod.GET, "/v1/sessao/*").hasRole("ADMIN")
        .antMatchers("/v1/voto/*").hasRole("ADMIN")
        .antMatchers("/v1/voto/voto").hasRole("COPERADO")
        .antMatchers(HttpMethod.POST, "/v1//auth/login").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
	
	// configuracaoes de recursos estaticos(CSS, imagens, etc)
    @Bean //corrigir o caminho.
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->  web.ignoring()
                .antMatchers("/**.html",
                        "/v2/api-docs/**",
                        "/webjars/**",
                        "/configuration/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**");   }
}
