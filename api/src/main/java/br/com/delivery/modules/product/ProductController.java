package br.com.delivery.modules.product;

import br.com.delivery.modules.crud.CrudController;
import br.com.delivery.modules.product.dtos.ProductDTO;
import br.com.delivery.modules.product.mapper.ProductMapper;
import br.com.delivery.modules.product.specifications.ProductSpec;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Product", description = "Endpoint related to product")
public class ProductController extends CrudController<Product, ProductRepository, ProductSpec, ProductDTO, ProductMapper> {


}
