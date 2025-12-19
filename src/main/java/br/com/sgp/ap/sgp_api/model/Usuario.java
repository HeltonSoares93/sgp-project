package br.com.sgp.ap.sgp_api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sgp.ap.sgp_api.enums.UsuarioStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank somente para strings, @NotNull para os demais casos
    @NotBlank(message = "O campo 'nome' é obrigatório") // não pode estar em branco
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O campo 'cpf' é obrigatório e deve ser único") // validação a nível de requisição
    @Column(nullable = false, unique = true) // validação a nível de banco de dados
    private String cpf;

    @NotBlank(message = "O campo 'email' é obrigatório")
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O campo 'senha' é obrigatório e com máx de 19 caracteres")
    @Size(max = 19)
    @Column(nullable = false, length = 19)
    private String senha;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UsuarioStatus status;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Projeto> projetos;

}
