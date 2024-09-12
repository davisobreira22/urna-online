package app.entity;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Eleitor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Informe o nome do eleitor.")
	private String nome;
	
	@Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$|^$", message = "CPF inválido!")
	private String cpf;
	
	@NotBlank(message = "Informe a profissão do eleitor.")
	private String profissao;
	
	@NotBlank(message="Por favor, informe o telefone do aluno")
	@Pattern(regexp = "\\(\\d{2}\\) ?\\d{5}-\\d{4}", message = "O telefone deve corresponder ao formato (xx) xxxxx-xxxx")
	private String celular;
	
	@Pattern(regexp = "\\(\\d{2}\\) ?\\d{5}-\\d{4}", message = "O telefone deve corresponder ao formato (xx) xxxx-xxxx")
	private String telefoneFixo;
	
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$|^$", message = "E-mail inválido!")
	private String email;
	
	private String status;
}
