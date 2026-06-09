package com.SpaceAgro.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_PRODUTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produtor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTOR")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "SENHA", nullable = false)
    private String senha;
}