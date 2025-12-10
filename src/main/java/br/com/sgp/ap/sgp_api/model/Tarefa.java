package br.com.sgp.ap.sgp_api.model;

import java.time.LocalDate;

import br.com.sgp.ap.sgp_api.enums.TarefaPrioridadeEnum;
import br.com.sgp.ap.sgp_api.enums.TarefaStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataCriacao;

    @Column(nullable = false)
    private LocalDate dataConclusao;

    @Column(nullable = false)
    private TarefaPrioridadeEnum prioridade;

    @Column(nullable = false)
    private TarefaStatusEnum status;

    @ManyToOne
    private Projeto projeto;

    @ManyToOne
    private Usuario usuario;

}
