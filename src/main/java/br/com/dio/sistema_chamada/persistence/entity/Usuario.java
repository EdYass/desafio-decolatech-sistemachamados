package br.com.dio.sistema_chamada.persistence.entity;

import lombok.Data;

@Data
public class Usuario {
    private Long id;
    private String nome;
    private String email;
}
