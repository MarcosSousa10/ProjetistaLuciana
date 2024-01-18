package com.projetista.othon.src.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetista.othon.src.Model.Titulos;
import com.projetista.othon.src.Model.user.AuthenticationDTO;
import com.projetista.othon.src.Model.user.LoginResponseDTO;
import com.projetista.othon.src.Repository.TitulosRepository;
import com.projetista.othon.src.Repository.UserRepository;
import com.projetista.othon.src.Repository.UserRepositorySecurit;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class ControllerUserLogin {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TitulosRepository titulosRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private com.projetista.othon.src.security.TokenService tokenService;

    @GetMapping("/TitulosEmAbertosSemAprovacao")
    public List<Titulos> selecttitulosaprovacao(
            @RequestParam(name = "DTINICIO ", defaultValue = "01/01/2000") String DTINICIO,
            @RequestParam(name = "DTFIM ", defaultValue = "01/01/2050") String DTFIM) {
        return titulosRepository.TITULOSEMABERTOSEMAPROVACAO(DTINICIO, DTFIM);
      
    }
    
    @GetMapping("/StatusProjetista")
    public List<Titulos> StatusProjetista(
            @RequestParam(name = "DTINICIO ", defaultValue = "01/01/2000") String DTINICIO,
            @RequestParam(name = "DTFIM ", defaultValue = "01/01/2050") String DTFIM) {
        return titulosRepository.StatusProjetista(DTINICIO, DTFIM);
    }
    @GetMapping("/StatusProjetistaEstorno")
    public List<Titulos> StatusProjetistaEstorno(
            @RequestParam(name = "DTINICIO ", defaultValue = "01/01/2000") String DTINICIO,
            @RequestParam(name = "DTFIM ", defaultValue = "01/01/2050") String DTFIM) {
        return titulosRepository.StatusProjetistaEstorno(DTINICIO, DTFIM);
    }
    
    @GetMapping("/StatusEstorno")
    public List<Titulos> StatusEstorno(
            @RequestParam(name = "DTINICIO ", defaultValue = "01/01/2000") String DTINICIO,
            @RequestParam(name = "DTFIM ", defaultValue = "01/01/2050") String DTFIM) {
        return titulosRepository.StatusEstorno(DTINICIO, DTFIM);
    }
    @GetMapping("/UpdataStatus/{status}/{numorcamento}")
    public ResponseEntity<String> UpdataStatus(@PathVariable String status, @PathVariable String numorcamento) {
        Integer result;
        if(status.equals("0")){
            result = titulosRepository.updateStatus1("",numorcamento);
        }else{
            result = titulosRepository.updateStatus(status,numorcamento);
        }
        System.out.println(result);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("Update Efetuado Com Sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Error Consulte o Setor de TI!");
        }
    }
    @GetMapping("/updateDataPagamento/{funcao}/{numorcamento}")
    public ResponseEntity<String>  updateDataPagamento(@PathVariable String funcao,@PathVariable String numorcamento) {
        Integer result;
        if(funcao.equals("criar")){
            result = titulosRepository.updateDataPagamento(numorcamento);
        }else{
            result = titulosRepository.updateApagarDataPagamento(numorcamento);
        }
        System.out.println(result);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("Update Efetuado Com Sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Error Consulte o Setor de TI!");
        }
    }
    
    
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        String senha = userRepository.ValidarNomeGuerra(data.login());
        String senhas = data.password();
        if (data.password().equals(senha)) {
            senhas = userRepository.senhaCripto(data.login());
        }
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), senhas);
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((com.projetista.othon.src.Model.user.User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
