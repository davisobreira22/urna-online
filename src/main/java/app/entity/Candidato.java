package app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Candidato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Informe o nome do candidato!")
	private String nome;
	
	@Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$|^$", message = "CPF inválido!")
	private String cpf;
	
	@Column(unique = true)
	@NotNull(message = "Informe o número do candidato!")
	private int numCandidato;
	
	@NotNull(message = "Informe a função do candidato!")
	@Min(1)@Max(2)
	private int funcao;
	
	private String status;
	
	@Transient
	private int votosApurados;
}
