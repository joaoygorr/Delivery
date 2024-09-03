package com.br.burguer.modules.size;

import com.br.burguer.modules.size.dto.SizeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
@Tag(name = "Size", description = "Size related endpoint")
public class SizeController {

    private final SizeService sizeService;

    @Autowired
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    @Operation(summary = "List all sizes", description = "Returns a list of sizes")
    public ResponseEntity<Page<SizeDTO>> getAll(Pageable pageable) {
        Page<SizeDTO> sizeDTOS = this.sizeService.getAllSizes(pageable);
        return ResponseEntity.ok(sizeDTOS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a size", description = "Returns a size based on id")
    public ResponseEntity<SizeDTO> getSize(@PathVariable Long id) {
        SizeDTO sizeDTO = this.sizeService.getComplementaryById(id);
        return ResponseEntity.ok(sizeDTO);
    }
}
