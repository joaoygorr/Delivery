package br.com.delivery.modules.crud;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends EntityBase, Id> extends JpaRepositoryImplementation<T, Id>,
        JpaSpecificationExecutor<T> {
}
