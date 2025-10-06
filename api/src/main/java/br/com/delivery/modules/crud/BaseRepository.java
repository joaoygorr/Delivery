package br.com.delivery.modules.crud;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean

public interface BaseRepository<T extends EntityBase, Id> extends JpaRepositoryImplementation<T, Id>,
        JpaSpecificationExecutor<T> {

    <T extends EntityBase> Optional<T> findById(Long id, Class<?> retorno);

    Object findAll(Specification<?> specs, Class<?> retorno, Pageable page);
}
