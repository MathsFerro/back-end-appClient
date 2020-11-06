package io.github.mathsferro.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "{campo.nome.obrigatorio}") // Faz a leitura da message que está no messages.properties e valida na InternacionalizacaoConfig // @NotEmpty -> tanto valida se é nulo ou vazia
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}") // Validador CPF do Hibernate
    private String cpf;

    @Column(name = "data_cadastro", updatable = false) // updatable = false nunca vai atualizar a coluna
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist // Quando a entidade pré persistir no banco ela vai executar esse metodo
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }
}
