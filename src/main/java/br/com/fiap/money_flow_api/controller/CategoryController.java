package br.com.fiap.money_flow_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_flow_api.model.Category;

@RestController // component
public class CategoryController {

    // Listar todas as categorias
    // GET :8080/categories -> retorna cógigo 200 (OK) -> json
    @GetMapping("/categories") // Mapeia a requisição para o método, dizendo o método e seu endpoint (caminho)
    public List<Category> index() { 
        // Vamos retornar um objeto JAVA para ser convertido em JSON
        return List.of(
            new Category(1L, "Educação", "book"),
            new Category(2L, "Lazer", "casino"),
            new Category(3L, "Salário", "payments")
        );
    }

    // Cadastrar categorias
    // POST

    
    // Apagar categorias
    // DELETE

}
