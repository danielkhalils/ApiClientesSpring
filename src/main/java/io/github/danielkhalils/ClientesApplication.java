package io.github.danielkhalils;

import io.github.danielkhalils.model.entity.Cliente;
import io.github.danielkhalils.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApplication {

    //Inserir um novo cliente chamando o método Build e persistindo com o método save
//    @Bean
//    public CommandLineRunner run(@Autowired ClienteRepository repository){
//        return args -> {
//            Cliente cliente = Cliente.builder().cpf("00000000000").nome("Fulano").build();
//            repository.save(cliente);
//        };
//    }
    public static void main(String[] args) {
        SpringApplication.run(ClientesApplication.class, args);
    }

}