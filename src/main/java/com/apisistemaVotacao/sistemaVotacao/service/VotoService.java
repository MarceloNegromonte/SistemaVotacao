package com.apisistemaVotacao.sistemaVotacao.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apisistemaVotacao.sistemaVotacao.dto.request.VotoRequestDTO;
import com.apisistemaVotacao.sistemaVotacao.exception.NotFoundException;
import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;
import com.apisistemaVotacao.sistemaVotacao.model.Usuario;
import com.apisistemaVotacao.sistemaVotacao.model.Voto;
import com.apisistemaVotacao.sistemaVotacao.repository.SessaoVotacaoRepository;
import com.apisistemaVotacao.sistemaVotacao.repository.VotoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
 	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    public VotoService(VotoRepository votoRepository, SessaoVotacaoRepository sessaoVotacaoRepository, SessaoService sessaoService, UsuarioService usuarioService) {
        this.votoRepository = votoRepository;
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.sessaoService = sessaoService;
        this.usuarioService = usuarioService;
    }
	
	@Transactional
	public Voto votacao(VotoRequestDTO dto) {
		SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(dto.getSessaoVotacaoId()).orElse(null);
		Usuario usuario = usuarioService.buscarPorCPF(dto.getCpf());
		log.info("Verificando sessao votacao");
		if (sessaoVotacao==null) {
			log.info("Sessao nao encontrada");
			throw new NotFoundException("Sessao votacao nao encontrada");
		}
		
		Voto voto = Voto.builder()
				.voto(dto.getVoto())
				.votoInstant(Instant.now())
				.usuario(usuario)
				.sessaoVotacao(sessaoVotacao).build();
		
		if (Boolean.TRUE.equals(verificarVotoUnico(sessaoVotacao, usuario))){

            throw new RuntimeException("Usuario ja votou");
        }
		
		verificaSessaoVotoValidoTempo(voto);
		
		log.info("Salvando o voto");
		return votoRepository.save(voto);
	}
	
	private Boolean verificarVotoUnico(SessaoVotacao sessaoVotacao, Usuario usuario){

        return votoRepository.existsBySessaoVotacaoAndUsuario(sessaoVotacao, usuario);
    }
	
	@Transactional
	private void verificaSessaoVotoValidoTempo(Voto voto) {
		log.info("Verificando tempo valido da sessao");
		if (voto.getVotoInstant().isAfter(voto.getSessaoVotacao().getFechado())) {
			log.info("Sessao expirada");
			throw new RuntimeException("Sessao votacao expirada");
		}
	}
    
}


