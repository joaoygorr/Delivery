package com.br.burguer.services.size;

import com.br.burguer.modules.size.dto.SizeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SizeService {
    Page<SizeDTO> getAllSizes(Pageable pageable);

    SizeDTO getSizeById(Long sizeId);
}
