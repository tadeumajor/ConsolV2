package com.consol.projeto.Consol.repository;

import com.consol.projeto.Consol.model.Condominio;
import com.consol.projeto.Consol.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Iterable<Usuario> findByCondominio(Condominio condominio);
    Usuario findByCpf(String cpf);

}
