package br.com.delivery.modules.crud;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({ @Spec(path = "id", spec = Equal.class)})
public interface BaseSpecs<T> extends Specification<T> {
}
