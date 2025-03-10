package br.com.dio.sistema_chamada.persistence.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Chamado {
    private Long id;
    private String descricao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;
    private Status status;
    private Long usuarioId;
}
