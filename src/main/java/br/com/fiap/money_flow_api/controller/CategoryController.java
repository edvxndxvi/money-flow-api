package br.com.fiap.money_flow_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_flow_api.model.Category;

@RestController // component
public class CategoryController {

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
        System.out.println("Cadastrando..." + category.getName());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    // Detalhes de uma categoria
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){
        System.out.println("Buscando categoria");
    
        var category = repository
    // STREAM divide cada um dos itens da lista para serem consumidos posteriormente, permitindo aplicar operações funcionais, como as seguintes
            .stream()
            .filter(c -> c.getId().equals(id)) // Verifica se o 'id' de cada elemento é igual ao 'id' informado no parâmetro.
            .findFirst(); // Retorna um optional de categoria (empty), porem ainda nao retorna a categoria, ainda precisa ser manipulado

        if(category.isEmpty()){
            System.out.println("Categoria não encontrada");
            return ResponseEntity.notFound().build(); // Retorna uma resposta com status 404 (Not Found) sem corpo, indicando que o recurso não foi encontrado.
        }

        return ResponseEntity.ok(category.get()); // Retorna uma resposta com status 200 (OK) e o recurso encontrado no corpo da resposta.
    }
    
    // Apagar categorias
    // DELETE

    // Editar categorias

}
