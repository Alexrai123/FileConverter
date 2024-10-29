package com.example.demo.Controller;

import com.example.demo.Model.Conversions;
import com.example.demo.Model.Users;
import com.example.demo.Repository.ConversionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversions")
public class ConversionsController {

    @Autowired
    private final ConversionsRepository conversionsRepository;

    @GetMapping
    public List<Conversions> getAllConversions() {
        return conversionsRepository.findAll();
    }

    @GetMapping("/{idConversion}")
    public Conversions getConversionById(@PathVariable Integer idConversion) {
        Optional<Conversions> conversion = conversionsRepository.findById(idConversion);
        if (conversion.isPresent()) {
            return conversion.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversion not found");
        }
    }

    @PostMapping
    public Conversions createConversion(@RequestBody  Conversions conversion) {
        conversionsRepository.save(conversion);
        return conversion;
    }

    @PutMapping
    public Conversions updateConversion(@RequestBody Conversions conversionToUpdate) {
        Conversions conversion = conversionsRepository.findById(conversionToUpdate.getConversionId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversion not found"));
        conversion.setSourceFormat(conversionToUpdate.getSourceFormat());
        conversion.setTargetFormat(conversionToUpdate.getTargetFormat());
        conversion.setStatus(conversionToUpdate.getStatus());
        conversion.setConvertedFilePath(conversionToUpdate.getConvertedFilePath());
        conversion.setConversionDate(conversionToUpdate.getConversionDate());
        return conversionsRepository.save(conversion);
    }

    @DeleteMapping("/{idConversion}")
    public void deleteConversion(@PathVariable Integer idConversion) {
        Conversions conversion = conversionsRepository.findById(idConversion).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversion not found"));
        conversionsRepository.delete(conversion);
    }
}
