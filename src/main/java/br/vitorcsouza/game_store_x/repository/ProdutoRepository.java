package br.vitorcsouza.game_store_x.repository;

import br.vitorcsouza.game_store_x.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
