package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Candidato;
import app.entity.Eleitor;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
	List<Candidato> findByStatus(String status);
	
	@Query("FROM Canditato c WHERE c.funcao = :funcao AND c.status = :status")
	List<Candidato> findByPrefeitoAtivo(int funcao, String statu);
	
	@Query("FROM Canditato c WHERE c.funcao = :funcao AND c.status = :status")
	List<Candidato> findByVereadorAtivo(int funcao, String status);
}
