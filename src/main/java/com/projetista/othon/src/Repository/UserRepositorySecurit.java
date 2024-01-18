package com.projetista.othon.src.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.projetista.othon.src.Model.user.User;




public interface UserRepositorySecurit extends JpaRepository<User, Long> {
    
    UserDetails findByLogin(String login);
    
}
