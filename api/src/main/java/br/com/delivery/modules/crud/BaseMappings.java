package br.com.delivery.modules.crud;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "dateCreated", ignore = true)
})
public @interface BaseMappings { }
