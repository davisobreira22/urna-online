package app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Apuracao;
import app.entity.Candidato;
import app.entity.Eleitor;
import app.entity.Voto;
import app.repository.EleitorRepository;
import app.repository.VotoRepository;

@Service
public class VotoService {

	@Autowired
	VotoRepository votoRepository;

	@Autowired
	EleitorRepository eleitorRepository;
	
	@Autowired
	CandidatoService candidatoService;

	public String salvarVoto(Voto voto, long id) {

		String hash = UUID.randomUUID().toString();
		Optional<Eleitor> eleitorBD = this.eleitorRepository.findById(id);
		
		if (eleitorBD.isPresent()) {
			if (eleitorBD.get().getStatus() == "PENDENTE") {
				eleitorBD.get().setStatus("BLOQUEADO");
				this.eleitorRepository.save(eleitorBD.get());
				throw new RuntimeException("Eleitor com cadastro pendente tentou votar. Eleitor será bloqueado!");
			} else if (eleitorBD.get().getStatus() == "APTO") {

				if (voto.getPrefeito().getFuncao() == 2) {
					throw new RuntimeException(
							"O candidato escolhido para prefeito é um candidato a vereador. Refaça a requisição!");
				}
				if (voto.getVereador().getFuncao() == 1) {
					throw new RuntimeException(
							"O candidato escolhido para vereador é um candidato a prefeito. Refaça a requisição!");
				}

				voto.setDataHora(LocalDateTime.now());
				voto.setComprovante(hash);
				Voto votoSalvo = this.votoRepository.save(voto);

				if (votoSalvo != null) {
					eleitorBD.get().setStatus("VOTOU");
					eleitorRepository.save(eleitorBD.get());
					return "Voto salvo com sucesso! Comprovante: "+hash;
				} else {
					throw new RuntimeException("Erro ao salvar o voto! Tente novamente!");
				}

			} else {
				throw new RuntimeException("Eleitor inapto para votar!");
			}
		}else {
			throw new RuntimeException("Eleitor não encontrado!");
		}	
	}
	
	public Apuracao realizarApuracao() {
		
		List<Candidato> candidatosPrefeito = this.candidatoService.findAllPrefeitosAtivos();
		List<Candidato> candidatosVereador = this.candidatoService.findAllVereadoresAtivos();
		
		for (Candidato candidato : candidatosPrefeito) {
	        int votosTotais = votoRepository.contarVotosPorCandidatoPrefeito(candidato.getId());
	        candidato.setVotosApurados(votosTotais);
	    }
		
		for (Candidato candidato : candidatosVereador) {
	        int votosTotais = votoRepository.contarVotosPorCandidatoVereador(candidato.getId());
	        candidato.setVotosApurados(votosTotais);
	    }
		
		candidatosPrefeito.sort((c1, c2) -> Integer.compare(c2.getVotosApurados(), c1.getVotosApurados()));
	    candidatosVereador.sort((c1, c2) -> Integer.compare(c2.getVotosApurados(), c1.getVotosApurados()));

	    Apuracao apuracao = new Apuracao();
	    apuracao.setPrefeitos(candidatosPrefeito);
	    apuracao.setVereador(candidatosVereador);

	    return apuracao;
	}

}
