package com.br.burguer.modules.size;

import com.br.burguer.exceptions.Exception404;
import com.br.burguer.modules.size.dto.SizeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SizeService {

    private final SizeRepository sizeRepository;

    @Autowired
    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public Page<SizeDTO> getAllSizes(Pageable pageable) {
        Page<Size> sizes = this.sizeRepository.findAll(pageable);
        return sizes.map(SizeDTO::toDto);
    }

    public SizeDTO getComplementaryById(Long sizeId) {
        Size size = this.sizeRepository.findById(sizeId)
                .orElseThrow(() -> new Exception404("Size with code " + sizeId + " not found!"));
        return SizeDTO.toDto(size);
    }
}
