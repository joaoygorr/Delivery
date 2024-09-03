package com.br.burguer.modules.complementary;

import com.br.burguer.modules.complementary.dto.ComplementaryDTO;
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
@RequestMapping("/complementary")
@Tag(name = "Complementary", description = "Complementary related endpoint")
public class ComplementaryController {

    private final ComplementaryService complementaryService;

    @Autowired
    public ComplementaryController(ComplementaryService complementaryService) {
        this.complementaryService = complementaryService;
    }

    @GetMapping
    @Operation(summary = "List all complementaries", description = "Returns a list of complementaries")
    public ResponseEntity<Page<ComplementaryDTO>> getAll(Pageable pageable) {
        Page<ComplementaryDTO> complementaryDTOS = this.complementaryService.getAllComplementaries(pageable);
        return ResponseEntity.ok(complementaryDTOS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a complementary", description = "Returns a complementary based on id")
    public ResponseEntity<ComplementaryDTO> getComplementary(@PathVariable Long id) {
        ComplementaryDTO complementaryDTO = this.complementaryService.getComplementaryById(id);
        return ResponseEntity.ok(complementaryDTO);
    }
}
