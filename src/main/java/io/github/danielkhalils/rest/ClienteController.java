package io.github.danielkhalils.rest;

import io.github.danielkhalils.model.entity.Cliente;
import io.github.danielkhalils.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    //Injetando a dependência ClienteRepository através do construtor
    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    //Criar cliente no servidor POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar( @Valid @RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    //Obter um cliente por ID e retornando um status not_found caso não exista
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    //Deletar um cliente
    //O metodo busca primeiro o cliente por id e depois deleta ele
    //Retorna not_found caso o cliente não exista
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Integer id){
        repository
                .findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    //Atualizando um cliente
    //O metodo busca primeiro o cliente por id e depois atualiza ele
    //Retorna not_found caso o cliente não exista
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        repository
                .findById(id)
                .map( cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(clienteAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
