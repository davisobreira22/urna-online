package app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Informe a data e hora do voto!")
	private LocalDateTime dataHora;
	
	@NotNull(message = "Informe o candidato a prefeito!")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Candidato prefeito;
	
	@NotNull(message = "Informe o candidato a vereador!")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Candidato vereador;
	
	
	private String comprovante;
}
