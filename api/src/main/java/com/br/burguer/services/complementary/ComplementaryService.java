package com.br.burguer.services.complementary;

import com.br.burguer.modules.complementary.dto.ComplementaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComplementaryService {

    Page<ComplementaryDTO> getAllComplementaries(Pageable pageable);

    ComplementaryDTO getComplementaryById(Long complementaryId);
}
