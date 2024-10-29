package com.example.demo.Controller;

import com.example.demo.Model.Files;
import com.example.demo.Repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private final FilesRepository filesRepository;

    @GetMapping
    public ResponseEntity<List<Files>> getAllFiles() {
        return ResponseEntity.ok(filesRepository.findAll());
    }

    @GetMapping("/{idFile}")
    public Files getOne(@PathVariable String idFile) {
        Optional<Files> file = filesRepository.findById(Integer.valueOf(idFile));
        if (file.isPresent()){
            return file.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
    }

    @PostMapping
    public Files createFile(@RequestBody Files file) {
        filesRepository.save(file);
        return file;
    }

    @PutMapping
    public Files updateFile(@RequestBody Files fileToUpdate) {
        Files file = filesRepository.findById(fileToUpdate.getFileId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
        file.setFileName(fileToUpdate.getFileName());
        file.setFileSize(fileToUpdate.getFileSize());
        file.setFileType(fileToUpdate.getFileType());
        file.setUploadDate(fileToUpdate.getUploadDate());
        return filesRepository.save(file);
    }

    @DeleteMapping("/{idFile}")
    public void deleteFile(@PathVariable Integer idFile) {
        Files file = filesRepository.findById(idFile).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
        filesRepository.delete(file);
    }
}
