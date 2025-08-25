package br.com.delivery.modules.category.specifications;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.crud.BaseSpecs;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Spec(path = "name", params = "name", spec = LikeIgnoreCase.class)
public interface CategorySpec extends BaseSpecs<Category> {
}
