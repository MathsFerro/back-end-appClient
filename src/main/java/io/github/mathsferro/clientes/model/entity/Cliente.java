package io.github.mathsferro.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

//@Getter @Setter -> Faz a função de Get e Set dos Métodos do Java ( Lombok é responsável por isso ), para reconhecer, precisa instalar o plugin do lombok e também adicionanr nas dependencias do projeto
@Entity
@Data // Cria todos os métodos necessários ( toString, getter, set... etc)
@NoArgsConstructor // Manter o construtor sem argumentos
@AllArgsConstructor // Faz com que o lombok gere um construtor com todos args/propriedades
@Builder // Acesso a um builder do cliente
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco que vai ser encarregado de popular o ID
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist // Quando a entidade pré persistir no banco ela vai executar esse metodo
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }
}
