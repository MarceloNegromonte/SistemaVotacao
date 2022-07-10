package com.apisistemaVotacao.sistemaVotacao.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.apisistemaVotacao.sistemaVotacao.model.Usuario;
import com.apisistemaVotacao.sistemaVotacao.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService {

	@Autowired
	CpfService cpfService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario buscarPorId(Long id) {
		log.info("Buscando por id {}", id);
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario buscarPorNome(String nome) {
		log.info("Buscando por nome {}", nome);
		return usuarioRepository.findByNome(nome).orElse(null);
	}
	 
	public Optional<Usuario> criarUsuario(Usuario usuario) {
		if (usuarioRepository.findByCpf(usuario.getCpf()).isPresent()) {
			log.error("CPF ja cadastrado {}", usuario.getCpf());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado", null);
		}
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			log.error("Email ja cadastrado {}", usuario.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EMAIL já cadastrado", null);
		}
		if (!cpfService.validarCpf(usuario.getCpf())) {
			log.error("CPF invalido {}", usuario.getCpf());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF não é válido", null);
		}

		usuario.setSenha(encriptyPassword(usuario.getSenha()));
		log.info("Salvando usuario");
		return Optional.of(usuarioRepository.save(usuario));
	}

    public Usuario atualizarUsuario(Usuario usuario){

        if (usuarioRepository.findById(usuario.getId()).isPresent()){
            Optional<Usuario> encontrarUsuario = usuarioRepository.findByEmail(usuario.getEmail());

            if (encontrarUsuario.isPresent() && !Objects.equals(encontrarUsuario.get().getId(), usuario.getId())){
                log.info("Usuario inexistente");    
            	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente", null);
            }

            if (Boolean.FALSE.equals(cpfService.validarCpf(usuario.getCpf()))){
                log.info("Cpf invalido");
            	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cpf invalido",null);
            }

            usuario.setSenha(encriptyPassword(usuario.getPassword()));
            log.info("Salvando usuario");
            return usuarioRepository.save(usuario);
        }
        log.info("Usuario nao encontrado");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado", null);
    }

	public void deletaUsuario(Long id){
		log.info("Deletando usuario");
        usuarioRepository.deleteById(id);
    }

	public String encriptyPassword (String password) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String passwordEncoder = enconder.encode(password);
		return passwordEncoder;
	}
	
	public Usuario buscarPorCPF(String cpf) {
		log.info("Buscando por CPF");
		return usuarioRepository.findByCpf(cpf).orElse(null);
	}

}