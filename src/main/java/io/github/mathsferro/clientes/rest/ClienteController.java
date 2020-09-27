package io.github.mathsferro.clientes.rest;

import io.github.mathsferro.clientes.model.entity.Cliente;
import io.github.mathsferro.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController // Serve para ser conhecida como Controllador Rest ou seja, que vai receber e enviar respostas HTTP Rest
@RequestMapping("/api/clientes") // Mapear a URL base
public class ClienteController {

    @Autowired
    private final ClienteRepository repository;

    @Autowired // Injeção Obrigatória, devido ao construtor
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping // Mapeia esse método para uma requisição POST
    @ResponseStatus(HttpStatus.CREATED) // Mapear para o corpo da resposta em formato JSON
    public Cliente addClient(@RequestBody Cliente cliente) { // Vai ser um JSON que vai vir do corpo da requisição por isso o @RequestBody
        return repository.save(cliente);
    }

    @GetMapping("{id}") // Essa anotação vai criar/será uma variável para utilizar no @PathVariable("id") ou só @PathVariable
    public Cliente getClientById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Código de sucesso, que não tem nenhum recurso de retorno
    public Cliente deleteClientById(@PathVariable("id") Integer id) {
        return repository
                .findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
