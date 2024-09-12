package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Eleitor;

public interface EleitorRepository extends JpaRepository<Eleitor, Long>{
	List<Eleitor> findByStatus(String status);
}
