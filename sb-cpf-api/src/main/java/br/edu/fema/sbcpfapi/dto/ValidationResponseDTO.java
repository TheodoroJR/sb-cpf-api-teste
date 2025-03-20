package br.edu.fema.sbcpfapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResponseDTO {

    private String cpf;
    private String status;
    private boolean valid;
    private String digits;

 }
