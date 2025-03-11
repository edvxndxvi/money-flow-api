package br.com.fiap.money_flow_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.money_flow_api.model.Category;

@RestController // component
public class CategoryController {
    private Logger log = LoggerFactory.getLogger(getClass());
    // Cria uma lista que vai salvar os posts
    private List<Category> repository = new ArrayList<>();

    // Listar todas as categorias
    // GET :8080/categories -> retorna cógigo 200 (OK) -> json
    @GetMapping("/categories") // Mapeia a requisição para o método, dizendo o método e seu endpoint (caminho)
    public List<Category> index() { 
        // Vamos retornar um objeto JAVA para ser convertido em JSON
        return repository;
    }

    // Cadastrar categorias
    // POST
    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category){ // ResponseEntity possibilita controlar o retorno da requisição
        log.info("Cadastrando..." + category.getName());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    // Detalhes de uma categoria
    @GetMapping("/categories/{id}")
    public Category get(@PathVariable Long id){
        log.info("Buscando categoria " + id);
        return getCategory(id);
    }
    
    // Apagar categorias
    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando categoria " + id);
        repository.remove(getCategory(id));
    }

    // Editar categorias
    @PutMapping("/categories/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category){
        log.info("Atualizando categoria " + id + " " + category);

        repository.remove(getCategory(id));
        category.setId(id);
        repository.add(category);

        return category; // 204
    }
    
    private Category getCategory(Long id) {
        // STREAM divide cada um dos itens da lista para serem consumidos posteriormente, permitindo aplicar operações funcionais, como as seguintes
        return repository
            .stream()
            .filter(c -> c.getId().equals(id)) // Verifica se o 'id' de cada elemento é igual ao 'id' informado no parâmetro.
            .findFirst()// Retorna um optional de categoria (empty), porem ainda nao retorna a categoria, ainda precisa ser manipulado
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );

    }
}
