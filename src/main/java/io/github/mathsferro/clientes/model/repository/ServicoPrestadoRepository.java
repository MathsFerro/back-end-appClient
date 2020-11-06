package io.github.mathsferro.clientes.model.repository;

import io.github.mathsferro.clientes.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Param Ela renomea o parametro, para usa-lo precisa colocar o : Ex -> :nome e :mes
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

    @Query(" SELECT s FROM ServicoPrestado s JOIN s.cliente c " +
            "WHERE upper( c.nome ) like upper ( :nome ) " +
            "AND MONTH(s.data) = :mes")
    List<ServicoPrestado> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);
}
