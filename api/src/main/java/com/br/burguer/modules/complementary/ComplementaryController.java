package com.br.burguer.modules.complementary;

import com.br.burguer.modules.complementary.dto.ComplementaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complementary")
public class ComplementaryController {

    private final ComplementaryService complementaryService;

    @Autowired
    public ComplementaryController(ComplementaryService complementaryService) {
        this.complementaryService = complementaryService;
    }

    @GetMapping
    public ResponseEntity<Page<ComplementaryDTO>> getAll(Pageable pageable) {
        Page<ComplementaryDTO> complementaryDTOS = this.complementaryService.getAllComplementaries(pageable);
        return ResponseEntity.ok(complementaryDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplementaryDTO> getComplementary(@PathVariable Long id) {
        ComplementaryDTO complementaryDTO = this.complementaryService.getComplementaryById(id);
        return ResponseEntity.ok(complementaryDTO);
    }
}
