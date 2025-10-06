package br.com.delivery.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utils {

    public static Class<?> getRetorno(String retorno) {
        try {
            if (!retorno.contains("modules"))
                retorno = "br.com.delivery.modules." + retorno;

            return Class.forName(retorno);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Pageable getUnpaged(Sort sort) {
        return Pageable.unpaged();
    }

    public static Pageable getPaged(Sort sort, Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    public static Pageable concatPagedId(Pageable pageable) {
        Sort sort = pageable.getSort().and(Sort.by("id"));
        return getPaged(sort, pageable);
    }
}
