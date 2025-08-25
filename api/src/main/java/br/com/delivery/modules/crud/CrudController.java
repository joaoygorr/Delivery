package br.com.delivery.modules.crud;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.ParameterizedType;
import java.util.function.Supplier;

/**
 * Crud controller generico.
 *
 * @param T define a entidade do controller.
 * @param R define o repositório da entidade.
 * @param S define as specifications da entidade.
 * @param D define o DTO.
 * @param M define o mapper da entidade.
 */
public abstract class CrudController<T extends EntityBase, R extends BaseRepository<T, Long>, S extends Specification<T>, D, M extends DtoMapper<T, D>> {

    @Autowired
    protected R repository;

    @Autowired
    protected M mapper;

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public CrudController() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional
    @PostMapping
    @Operation(summary = "Criar entidade.")
    public ResponseEntity<T> store(@RequestBody @Valid D dto) {
        Supplier<T> entitySupplier = () -> {
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Erro ao criar instância da entidade.", e);
            }
        };

        T entidadeT = entitySupplier.get();
        mapper.toEntity(dto, entidadeT);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entidadeT));
    }
}
