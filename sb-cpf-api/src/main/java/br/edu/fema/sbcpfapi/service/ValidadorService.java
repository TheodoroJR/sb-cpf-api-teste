package br.edu.fema.sbcpfapi.service;

import br.edu.fema.sbcpfapi.dto.ValidationResponseDTO;

public class ValidadorService {

    public ValidationResponseDTO estaValido(String cpf) {
        //PROCESSAMENTO

        String digitosVerificadores = lerDigitosVerificadores(cpf);

        String primeiroDigito = recuperarPrimeiroDigito(cpf);

        String segundoDigito = recuperarSegundoDigito(cpf, primeiroDigito);

        String digitosVerificadoresEncontrados = primeiroDigito + segundoDigito;

        boolean cpfEstaValido = digitosVerificadoresEncontrados.equals(digitosVerificadores);


        //RETORNO
        ValidationResponseDTO validationResponseDTO = new ValidationResponseDTO();

        validationResponseDTO.setValid(cpfEstaValido);
        validationResponseDTO.setCpf(cpf);
        validationResponseDTO.setStatus(gerarStatus(cpfEstaValido));
        validationResponseDTO.setDigits(primeiroDigito + segundoDigito);

        return validationResponseDTO;

    }

    private String gerarStatus(boolean cpfEstaValido) {
        if (cpfEstaValido) {
            return "CPF válido";
        }
        return "CPF inválido";
    }

    public String recuperarSegundoDigito(String cpf, String primeiroDigito) {
        String primeirosNoveDigitosComPrimeiroDigitoVerificador = cpf.substring(0, 9) + primeiroDigito;
        char[] digitos = primeirosNoveDigitosComPrimeiroDigitoVerificador.toCharArray();
        Integer soma = 0;
        Integer multiplicador = 11;

        for (int i = 0; i < digitos.length; i++) {
            soma = soma + Character.getNumericValue(digitos[i]) * multiplicador;
            multiplicador--;
        }

        Integer resto = soma % 11;
        if (resto < 2) {
            return "0";
        } else {
            return String.valueOf(11 - resto);
        }
    }

    public String recuperarPrimeiroDigito(String cpf) {
        String primeirosNoveDigitos = cpf.substring(0, 9);
        char[] digitos = primeirosNoveDigitos.toCharArray();
        Integer soma = 0;
        Integer multiplicador = 10;

        for (int i = 0; i < digitos.length; i++) {
            soma = soma + Character.getNumericValue(digitos[i]) * multiplicador;
            multiplicador--;
        }

        Integer resto = soma % 11;
        if (resto < 2) {
            return "0";
        } else {
            return String.valueOf(11 - resto);
        }

    }

    private String lerDigitosVerificadores(String cpf) {
        return cpf.substring(9);

    }

}
