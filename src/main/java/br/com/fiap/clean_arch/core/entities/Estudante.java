package br.com.fiap.clean_arch.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

@Getter
@EqualsAndHashCode
public class Estudante {

    private String identificadorInterno;
    private String nome;
    private String email;
    private Integer idade;

    public Estudante(String identificadorInterno, String nome, String email, Integer idade) {
        isNomeValido(nome);
        isIdadeValida(idade);
        isEmailValido(email);

        this.identificadorInterno = identificadorInterno;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public static Estudante create(String identificadorInterno, String nome, String email, Integer idade) {
        return new Estudante(identificadorInterno, nome, email, idade);
    }

    public static Estudante create(String nome, String email, Integer idade) {
        return new Estudante(null, nome, email, idade);
    }

    private static void isNomeValido(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (nome.trim().isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
    }

    private static void isIdadeValida(Integer idade) {
        if (idade == null) {
            throw new IllegalArgumentException("Idade inválido");
        }

        if (idade < 18) {
            throw new IllegalArgumentException("Idade deve ser maior que 18");
        }
    }

    private static void isEmailValido(String email) {
        var emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(email)) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }
}
