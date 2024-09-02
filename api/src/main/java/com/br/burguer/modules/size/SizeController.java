package com.br.burguer.modules.size;

import com.br.burguer.modules.size.dto.SizeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
public class SizeController {

    private final SizeService sizeService;

    @Autowired
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public ResponseEntity<Page<SizeDTO>> getAll(Pageable pageable) {
        Page<SizeDTO> sizeDTOS = this.sizeService.getAllSizes(pageable);
        return ResponseEntity.ok(sizeDTOS);
    }
}
