package com.example.demo.Conversions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversions")
public class ConversionsController {

    private final ConversionsService conversionsService;

    @GetMapping("/all")
    public List<Conversions> getAll() {
        return conversionsService.getAll();
    }

    @GetMapping("/allDTO")
    public List<ConversionsDTO> getAllDTO(){
        return conversionsService.getAllDTO();
    }

    @GetMapping("/{idConversion}")
    public Conversions getOne(@PathVariable Integer idConversion) {
        Optional<Conversions> conversion = Optional.ofNullable(conversionsService.getOne(idConversion));
        if (conversion.isPresent()) {
            return conversion.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversion not found");
        }
    }

    @PostMapping
    public Conversions create(@RequestBody ConversionsDTO conversion) {
        return conversionsService.create(conversion);
    }

    @PutMapping
    public Conversions update(@RequestBody Conversions conversion) {
        return conversionsService.update(conversion);
    }

    @DeleteMapping("/{idConversion}")
    public void delete(@PathVariable Integer idConversion) throws Exception {
        conversionsService.delete(idConversion);
    }
}
