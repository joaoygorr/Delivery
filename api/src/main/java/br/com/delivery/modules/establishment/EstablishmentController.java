package br.com.delivery.modules.establishment;

import br.com.delivery.modules.crud.CrudController;
import br.com.delivery.modules.establishment.dtos.EstablishmentDTO;
import br.com.delivery.modules.establishment.mapper.EstablishmentMapper;
import br.com.delivery.modules.establishment.specifications.EstablishmentSpec;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/establishment")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Establishment", description = "Endpoint related to establishment")
public class EstablishmentController extends CrudController<Establishment, EstablishmentRepository, EstablishmentSpec,
        EstablishmentDTO, EstablishmentMapper> {
}
