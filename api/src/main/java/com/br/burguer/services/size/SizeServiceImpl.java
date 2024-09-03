package com.br.burguer.services.size;

import com.br.burguer.exceptions.Exception404;
import com.br.burguer.modules.size.Size;
import com.br.burguer.modules.size.SizeRepository;
import com.br.burguer.record.size.SizeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Autowired
    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Page<SizeDTO> getAllSizes(Pageable pageable) {
        Page<Size> sizes = this.sizeRepository.findAll(pageable);
        return sizes.map(SizeDTO::toDto);
    }

    @Override
    public SizeDTO getSizeById(Long sizeId) {
        Size size = this.sizeRepository.findById(sizeId)
                .orElseThrow(() -> new Exception404("Size with code " + sizeId + " not found!"));
        return SizeDTO.toDto(size);
    }
}
