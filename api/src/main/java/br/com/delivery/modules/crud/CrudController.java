package br.com.delivery.modules.crud;

import br.com.delivery.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;
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

    private static final String PARAM_ID = "/{id}";

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

    @GetMapping
    @Operation(summary = "Listar entidades paginadas.")
    public ResponseEntity<Object> index(S specs, @ParameterObject Pageable page) {
        page = Utils.concatPagedId(page);
        if (page.isPaged()) {
            return ResponseEntity.ok(repository.findAll(specs, page));
        } else {
            return ResponseEntity.ok(repository.findAll(specs, page.getSort()));
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar entidades paginadas com fallback para listagem completa.")
    public ResponseEntity<Object> dropdown(@Parameter(description = "Especificações da consulta.", in = ParameterIn.QUERY) S specs,
                                           @Parameter(description = "Informações de paginação (padrão: page = 9999999).", in = ParameterIn.QUERY)
                                           @ParameterObject @PageableDefault(page = 9999999) Pageable page) {
        if (page.getPageNumber() == 9999999) {
            return index(specs, Utils.getUnpaged(page.getSort()));
        } else {
            return index(specs, page);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar entidade por ID.")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> show(@PathVariable @Parameter(description = "ID da entidade a ser buscada.") Long id) {
        Optional<T> obj = this.repository.findById(id);
        return obj.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar entidade por ID.")
    public ResponseEntity<T> update(@RequestBody @Valid D dto, @PathVariable @Parameter(description = "ID da entidade a ser atualizada.") Long id) {
        T entidadeSalva = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada com ID " + id));
        entidadeSalva = mapper.updateFromDto(entidadeSalva, dto);
        return ResponseEntity.ok(repository.save(entidadeSalva));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de entidade por ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Parameter(description = "ID da entidade a ser excluída.") Long id) {
        try {
            repository.deleteById(id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // Ignora se o registro não existe
        }
    }
}
