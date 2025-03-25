package br.com.fiap.money_flow_api.repository;

import br.com.fiap.money_flow_api.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
        
}
