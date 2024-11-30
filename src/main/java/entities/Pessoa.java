package entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@ToString

@Getter
@Setter
public class Pessoa {
    private int id;

    private String nome;

    private String email;

    private String cpf;

    private int idade;
}
