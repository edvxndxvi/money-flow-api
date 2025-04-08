package br.com.fiap.money_flow_api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Anotações JPA (@Entity e @Id) são usadas para mapear a classe Category como uma entidade no banco de dados. 
@Entity
// Anotações Lombok @Getter e @Setter sao usados para gerar os getters e setters. 
@Data
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório") // Verifica se todos os caracteres são espaços ou nulos
    @Size(min = 3, max = 50, message = "Deve ter pelo menos 3 caracters") // Delimita o tamanho da string permitido
    private String name;

    @NotBlank(message = "Campo obrigatório") 
    @Pattern(regexp = "^#([A-Z].*", message = "Deve comecar com uma letra maiuscula") // Criando uma expressão regular para validar o formato da string, tendo que começar com letra maiuscula
    private String icon;

}
