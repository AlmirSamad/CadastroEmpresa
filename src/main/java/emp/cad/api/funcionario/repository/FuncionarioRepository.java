package emp.cad.api.funcionario.repository;

import emp.cad.api.funcionario.entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Modifying
    @Query("UPDATE Funcionario f SET f.ativo = false WHERE f.empresa.id = :empresaId")
    void inativarTodosPorEmpresaId(Long empresaId);

    Page<Funcionario> findAllByAtivoTrue(Pageable pageable);

    @Modifying
    @Query("UPDATE Funcionario f SET f.ativo = true WHERE f.empresa.id = :empresaId")
    void ativarTodosPorEmpresaId(Long empresaId);
}
