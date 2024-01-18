package com.projetista.othon.src.Repository;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.projetista.othon.src.Model.Titulos;

import jakarta.transaction.Transactional;

public interface TitulosRepository extends JpaRepository<Titulos,Long>{
    @Query(value = "SELECT PROJETISTA, C.NUMORCA, T.CLIENTE , T.TIPOFJ AS TIPOCLIENTE , C.vlatend AS VALOR , C.vlcomissaoproj, C.dtpagcomissaoproj, C.statusprojetista, C.dtaprovcomissao " +
            "FROM PCORCAVENDAC C, PCCLIENT T " +
            "WHERE " +
            "C.DATA BETWEEN TO_DATE(?1, 'DD/MM/YYYY') AND TO_DATE(?2, 'DD/MM/YYYY') AND " +
            "C.CODCLI = T.CODCLI AND " +
            "(C.PROJETISTA IS NOT NULL OR C.PROJETISTA <> '') AND " +
            "C.VLCOMISSAOPROJ > 0 AND " +
            "(C.statusprojetista IS NULL OR C.statusprojetista = '') AND " +
            "(C.dtpagcomissaoproj IS NULL OR C.dtpagcomissaoproj = '') AND " +
            "C.POSICAO IN ('L', 'M','F')", nativeQuery = true)
    List<Titulos> TITULOSEMABERTOSEMAPROVACAO(String DTINICIO,String  DTFIM);
    @Query(value = "SELECT PROJETISTA, C.NUMORCA, T.CLIENTE , T.TIPOFJ AS TIPOCLIENTE , C.vlatend AS VALOR , C.vlcomissaoproj, C.dtpagcomissaoproj, C.statusprojetista, C.dtaprovcomissao "+
    "FROM PCORCAVENDAC C, PCCLIENT T "+
    "WHERE "+
    "C.DATA BETWEEN TO_DATE(?1, 'DD/MM/YYYY') AND  TO_DATE(?2, 'DD/MM/YYYY') AND "+
    "C.CODCLI = T.CODCLI AND "+
    "(C.PROJETISTA IS NOT NULL OR C.PROJETISTA <> '') AND "+
    "C.VLCOMISSAOPROJ > 0 AND "+
    "(C.statusprojetista IS NOT NULL OR C.statusprojetista <> '') AND "+
    "(C.dtpagcomissaoproj IS NULL OR C.dtpagcomissaoproj = '') and (c.statusprojetista = 'AP') ", nativeQuery = true)
    List<Titulos> StatusProjetista(String DTINICIO,String  DTFIM);
    @Query(value = "SELECT PROJETISTA, C.NUMORCA, T.CLIENTE , T.TIPOFJ AS TIPOCLIENTE , C.vlatend AS VALOR , C.vlcomissaoproj, C.dtpagcomissaoproj, C.statusprojetista, C.dtaprovcomissao "+
    "FROM PCORCAVENDAC C, PCCLIENT T "+
    "WHERE "+
    "C.DATA BETWEEN TO_DATE(?1, 'DD/MM/YYYY') AND  TO_DATE(?2, 'DD/MM/YYYY') AND "+
    "C.CODCLI = T.CODCLI AND "+
    "(C.PROJETISTA IS NOT NULL OR C.PROJETISTA <> '') AND "+
    "C.VLCOMISSAOPROJ > 0 AND "+
    "(C.statusprojetista IS NOT NULL OR C.statusprojetista <> '') AND "+
    "(C.dtpagcomissaoproj IS NULL OR C.dtpagcomissaoproj = '') ", nativeQuery = true)
    List<Titulos> StatusProjetistaEstorno(String DTINICIO,String  DTFIM);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)   
    @Query(value = "UPDATE PCORCAVENDAC c SET c.STATUSPROJETISTA = ?1 , c.dtaprovcomissao=SYSDATE WHERE c.numorca = ?2", nativeQuery = true)
    Integer updateStatus(String status, String numorcamento);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)   
    @Query(value = "UPDATE PCORCAVENDAC c SET c.STATUSPROJETISTA = ?1 , c.dtaprovcomissao= null WHERE c.numorca = ?2", nativeQuery = true)
    Integer updateStatus1(String status, String numorcamento);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)   
    @Query(value = "UPDATE PCORCAVENDAC c SET c.DTPAGCOMISSAOPROJ  = SYSDATE WHERE c.numorca = ?1", nativeQuery = true)
    Integer updateDataPagamento(String numorcamento);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)   
    @Query(value = "UPDATE PCORCAVENDAC c SET c.DTPAGCOMISSAOPROJ  = '' WHERE c.numorca = ?1", nativeQuery = true)
    Integer updateApagarDataPagamento(String numorcamento);
    @Query(value = "SELECT PROJETISTA, C.NUMORCA, T.CLIENTE , T.TIPOFJ AS TIPOCLIENTE , C.vlatend AS VALOR , C.vlcomissaoproj, C.dtpagcomissaoproj, C.statusprojetista, C.dtaprovcomissao "+
    "FROM PCORCAVENDAC C, PCCLIENT T "+
    "WHERE "+
    "C.DATA BETWEEN TO_DATE(?1, 'DD/MM/YYYY') AND TO_DATE(?2, 'DD/MM/YYYY')  AND "+
    "C.CODCLI = T.CODCLI AND "+
    "(C.PROJETISTA IS NOT NULL OR C.PROJETISTA <> '') AND "+
    "C.VLCOMISSAOPROJ > 0 AND "+
    "(C.statusprojetista IS NOT NULL OR C.statusprojetista <> '') AND "+
    "(C.dtpagcomissaoproj IS NOT NULL OR C.dtpagcomissaoproj <> '')", nativeQuery = true)
    List<Titulos> StatusEstorno(String DTINICIO,String  DTFIM);
} 
// 3228699

