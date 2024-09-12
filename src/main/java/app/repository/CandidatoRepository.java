package app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    
    // Método para encontrar candidatos por status
    List<Candidato> findByStatus(String status);
    
    // Query corrigida para encontrar prefeito ativo
    @Query("FROM Candidato c WHERE c.funcao = :funcao AND c.status = :status")
    List<Candidato> findByPrefeitoAtivo(@Param("funcao") int funcao, @Param("status") String status);
    
    // Query corrigida para encontrar vereador ativo
    @Query("FROM Candidato c WHERE c.funcao = :funcao AND c.status = :status")
    List<Candidato> findByVereadorAtivo(@Param("funcao") int funcao, @Param("status") String status);
}
