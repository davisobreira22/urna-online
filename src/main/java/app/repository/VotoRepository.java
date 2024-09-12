package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    
    // Contar votos para prefeito
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.prefeito.id = :candidatoId")
    public int contarVotosPorCandidatoPrefeito(@Param("candidatoId") Long candidatoId);

    // Contar votos para vereador
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.vereador.id = :candidatoId")
    public int contarVotosPorCandidatoVereador(@Param("candidatoId") Long candidatoId);
}

