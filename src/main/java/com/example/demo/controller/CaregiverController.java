package com.example.demo.controller;

import com.example.demo.dto.CaregiverDto;
import com.example.demo.service.CaregiverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/caregiver")
@RequiredArgsConstructor
public class CaregiverController {
    private final CaregiverService caregiverService;

    @GetMapping
    public ResponseEntity<List<CaregiverDto>> getAllCaregivers() {
        List<CaregiverDto> caregivers = caregiverService.getAll();
        if (caregivers == null || caregivers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(caregivers, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaregiverDto> getCaregiverById(@PathVariable Long id) {
        return caregiverService.getById(id)
                .map(dto -> new ResponseEntity<>(dto, OK))
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<CaregiverDto> createCaregiver(@RequestBody @Valid CaregiverDto caregiverDto) {
        return new ResponseEntity<>(caregiverService.create(caregiverDto), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaregiverDto> updateCaregiver(@PathVariable Long id, @RequestBody @Valid CaregiverDto caregiverDto) {
        return caregiverService.updateById(id, caregiverDto)
                .map(dto -> new ResponseEntity<>(dto, OK))
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CaregiverDto> deleteCaregiver(@PathVariable Long id) {
        return caregiverService.deleteById(id)
                .map(dto -> new ResponseEntity<>(dto, OK))
                .orElse(ResponseEntity.noContent().build());
    }
}
