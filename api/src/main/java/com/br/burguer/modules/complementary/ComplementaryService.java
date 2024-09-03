package com.br.burguer.modules.complementary;

import com.br.burguer.exceptions.Exception404;
import com.br.burguer.modules.complementary.dto.ComplementaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComplementaryService {

    private final ComplementaryRepository complementaryRepository;

    @Autowired
    public ComplementaryService(ComplementaryRepository complementaryRepository) {
        this.complementaryRepository = complementaryRepository;
    }

    public Page<ComplementaryDTO> getAllComplementaries(Pageable pageable) {
        Page<Complementary> complementaries = this.complementaryRepository.findAll(pageable);
        return complementaries.map(ComplementaryDTO::toDto);
    }

    public ComplementaryDTO getComplementaryById(Long complementaryId) {
        Complementary complementary = this.complementaryRepository.findById(complementaryId)
                .orElseThrow(() -> new Exception404("Complementary with code " + complementaryId + " not found!"));
        return ComplementaryDTO.toDto(complementary);
    }
}
