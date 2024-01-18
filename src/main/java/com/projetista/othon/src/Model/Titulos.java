package com.projetista.othon.src.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@Data
@Table(name = "PCORCAVENDAC")
public class Titulos {
    @Id
    private Long numorca;
    private String projetista;
    private String cliente;
    private String tipocliente;
    private BigDecimal valor;
    private BigDecimal vlcomissaoproj;
    private String dtpagcomissaoproj;
    private String statusprojetista;
    private String dtaprovcomissao;

}
