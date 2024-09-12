package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Eleitor;
import app.repository.EleitorRepository;

@Service
public class EleitorService {

	@Autowired
	EleitorRepository eleitorRepository;

	public Eleitor save(Eleitor eleitor) {
		if (eleitor.getCpf() == null || eleitor.getEmail() == null) {
			eleitor.setStatus("PENDENTE");
		} else {
			eleitor.setStatus("APTO");
		}

		return this.eleitorRepository.save(eleitor);
	}

	public Eleitor update(Eleitor eleitor, long id) {
		eleitor.setId(id);
		eleitor = verificarStatus(eleitor);
		return this.eleitorRepository.save(eleitor);
	}

	public Eleitor verificarStatus(Eleitor eleitor) {

		Optional<Eleitor> eleitorBD = this.eleitorRepository.findById(eleitor.getId());

		if (eleitorBD.isPresent()) {
			if (eleitorBD.get().getStatus() == "INATIVO") {
				eleitor.setStatus("INATIVO");
			} else if (eleitorBD.get().getStatus() == "BLOQUEADO") {
				eleitor.setStatus("BLOQUEADO");
			} else if (eleitor.getCpf() != null && eleitor.getEmail() != null) {
				eleitor.setStatus("APTO");
			} else if (eleitor.getCpf() == null || eleitor.getEmail() == null) {
				eleitor.setStatus("PENDENTE");
			}
		} else {
			return null;
		}

		return eleitor;
	}

	public List<Eleitor> findAll() {
		return this.eleitorRepository.findByStatus("APTO");
	}

	public String delete(long id) {

		Optional<Eleitor> eleitorBD = this.eleitorRepository.findById(id);

		if (eleitorBD.isPresent()) {
			if (eleitorBD.get().getStatus() == "VOTOU") {
				throw new RuntimeException("Eleitor já votou. Não foi possível inativá-lo!");	
			}
			eleitorBD.get().setStatus("INATIVO");
			Eleitor eleitorDeletado = this.eleitorRepository.save(eleitorBD.get());
			
			if(eleitorDeletado != null) {
				return "Eleitor inativado com sucesso!";
			}else {
				throw new RuntimeException("Erro ao inativar eleitor!");
			}
		}
		else {
			throw new RuntimeException("Eleitor não encontrado!");
		}
		
	}

}
