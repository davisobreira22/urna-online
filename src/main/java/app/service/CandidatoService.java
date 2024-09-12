package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Candidato;
import app.repository.CandidatoRepository;

@Service
public class CandidatoService {

	@Autowired
	CandidatoRepository candidatoRepository;
	
	public Candidato save(Candidato candidato) {
		candidato.setStatus("ATIVO");
		return this.candidatoRepository.save(candidato);
	}
	
	public Candidato update(Candidato candidato, long id) {
		candidato.setId(id);
		return this.candidatoRepository.save(candidato);
	}
	
	public List<Candidato> findAll(){
		return this.candidatoRepository.findByStatus("ATIVO");
	}
	
	public List<Candidato> findAllPrefeitosAtivos(){
		return this.candidatoRepository.findByPrefeitoAtivo(1, "ATIVO");
	}
	
	public List<Candidato> findAllVereadoresAtivos(){
		return this.candidatoRepository.findByVereadorAtivo(2, "ATIVO");
	}
	
	public String delete(long id) {
		Optional<Candidato> candidatoBD = this.candidatoRepository.findById(id);

		if (candidatoBD.isPresent()) {
			candidatoBD.get().setStatus("INATIVO");
			Candidato candidatoDeletado = this.candidatoRepository.save(candidatoBD.get());
			
			if(candidatoDeletado != null) {
				return "Candidato desativado com sucesso!";
			}else {
				return "Erro ao desativar cadidato!";
			}
			
		}else {
			return null;
		}
	}
}
