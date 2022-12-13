package com.consol.projeto.Consol.repository;

import com.consol.projeto.Consol.model.Condominio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CondominioRepository extends CrudRepository<Condominio, Long> {

    Condominio findByCodigo(long codigo);
}
