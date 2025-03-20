package br.edu.fema.sbcpfapi.controller;

import br.edu.fema.sbcpfapi.dto.ValidationResponseDTO;
import br.edu.fema.sbcpfapi.service.ValidadorService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/api")
public class ValidadorController {

    @GetMapping (value = "/cpf/{cpf}")
    public ResponseEntity<ValidationResponseDTO> teste(@PathVariable String cpf){
        ValidationResponseDTO resultado = new ValidadorService().estaValido(cpf);
        if(resultado.isValid()){
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(resultado);
        }
    }

}
