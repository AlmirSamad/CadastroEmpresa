package emp.cad.api.empresa.repository;

import emp.cad.api.empresa.entity.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
   Optional<Empresa> findByCnpj(String cnpj);

    Page<Empresa> findAllByAtivoTrue(Pageable pageable);
}
