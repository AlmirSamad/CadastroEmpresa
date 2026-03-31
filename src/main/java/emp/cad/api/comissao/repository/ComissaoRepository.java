package emp.cad.api.comissao.repository;

import emp.cad.api.comissao.entity.Comissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComissaoRepository extends JpaRepository<Comissao, Long> {
}
