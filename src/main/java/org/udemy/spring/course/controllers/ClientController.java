package org.udemy.spring.course.controllers;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.udemy.spring.course.domain.bo.Client;
import org.udemy.spring.course.repositories.ClientDAO;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientDAO dao;

    public ClientController(ClientDAO dao) {
        this.dao = dao;
    }

    @GetMapping("{id}")
    public Client get(@PathVariable Integer id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping()
    public Client post(@RequestBody
                       @Valid Client client) {
        var data = dao.save(client);
        if (Objects.isNull(data))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        return data;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        dao.findById(id).map(d -> {
            dao.delete(d);
            return true;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return false;
    }

    @PutMapping("{id}")
    public Client put(@PathVariable Integer id,
                      @RequestBody
                      @Valid Client client) {
        return dao.findById(id).map(d -> {
            client.setId(d.getId());
            dao.save(d);
            return d;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public List<Client> get(Client filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Client> list = dao.findAll(example);
        return list;
    }
}
