package br.vitorcsouza.game_store_x.repository;

import br.vitorcsouza.game_store_x.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
