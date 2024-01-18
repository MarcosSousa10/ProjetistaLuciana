package com.projetista.othon.src.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetista.othon.src.Model.user.User;


public interface UserRepository extends JpaRepository<User,Long>{
    @Query(value = "SELECT DECRYPT(PCEMPR.SENHABD, PCEMPR.nome_guerra) AS SENHA FROM PCEMPR WHERE NOME_GUERRA = ?1", nativeQuery = true)
    String ValidarNomeGuerra(String usuario);
    @Query(value = "select senhabd from PCEMPR where nome_guerra=?1", nativeQuery = true)
    String senhaCripto(String usuario);
}
