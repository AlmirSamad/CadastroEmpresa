package emp.cad.api.reuniao.repository;

import emp.cad.api.reuniao.entity.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {


    boolean existsByComissaoIdAndDataHora(Long idComissao, LocalDateTime dataHora);
}