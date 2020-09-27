package io.github.mathsferro.clientes;

import io.github.mathsferro.clientes.model.entity.Cliente;
import io.github.mathsferro.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientesApplication.class, args);
    }
}




    /*
    // Injeção de Dependência -> @Autowired
    @Bean
    // Primeiro metódo que sera analisado e rodado quando iniciar aplicação
    public CommandLineRunner run( @Autowired ClienteRepository repository) {
        return args -> {
            // Build gera instancia com essas propriedades, builder preenche as informações como se fosse um formulário (tem que adicionar na classe o @Build tb)
            Cliente cliente = Cliente.builder()
                                .cpf("12345678900")
                                .nome("Fulano").build();

            repository.save(cliente);
        };
    }
    */