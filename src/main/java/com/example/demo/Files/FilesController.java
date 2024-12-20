package com.example.demo.Files;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
//@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FilesController {

    private final FilesService filesService;

    @GetMapping("/all")
    public List<Files> getAll() {
        return filesService.getAll();
    }

    @GetMapping("/allDTO")
    public List<FilesDTO> getAllDTO() {
        return filesService.getAllDTO();
    }

    @GetMapping("/{idFile}")
    public Files getOne(@PathVariable Integer idFile) {
        try{
            return filesService.getOne(idFile);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID format", e);
        }
    }

    @PostMapping
    public Files create(@RequestBody FilesDTO file) {
        return filesService.create(file);
    }

    @PutMapping
    public Files update(@RequestBody Files fileToUpdate) {
        return filesService.update(fileToUpdate);
    }

    @DeleteMapping("/{idFile}")
    public void delete(@PathVariable Integer idFile) throws Exception {
        filesService.delete(idFile);
    }
}
