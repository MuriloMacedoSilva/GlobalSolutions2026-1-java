package com.SpaceAgro.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_TALHAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Talhao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TALHAO")
    private Long id;

    @Column(name = "NOME_TALHAO")
    private String nomeTalhao;

    @Column(name = "CULTURA")
    private String cultura;

    @Column(name = "AREA_HECTARES")
    private BigDecimal areaHectares;

    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    @Column(name = "ID_PRODUTOR")
    private Long idProdutor;
}