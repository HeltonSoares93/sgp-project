package br.com.sgp.ap.sgp_api.model;

import br.com.sgp.ap.sgp_api.enums.TarefaPrioridadeEnum;
import br.com.sgp.ap.sgp_api.enums.TarefaStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataCriacao;

    @Column(nullable = false)
    private LocalDate dataConclusao;

    @Column(nullable = false)
    private TarefaPrioridadeEnum prioridade;

    @Column(nullable = false)
    private TarefaStatusEnum status;


}
