package br.com.fiap.money_flow_api.model;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// Anotações JPA (@Entity e @Id) são usadas para mapear a classe Category como uma entidade no banco de dados. 
@Entity
// Anotações Lombok @Getter e @Setter sao usados para gerar os getters e setters. 
@Data
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String icon;

}
