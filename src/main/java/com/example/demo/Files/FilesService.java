package com.example.demo.Files;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilesService {

    private final FilesRepository filesRepository;

    public Files create(FilesDTO file) {
        return filesRepository.save(Files.builder()
                .fileName(file.getFileName())
                .fileSize(file.getFileSize())
                .fileType(file.getFileType())
                .uploadDate(file.getUploadDate())
                .build());
    }

    public Files getOne(Integer idFile) {
        return filesRepository.findById(idFile).orElseThrow(() -> new EntityNotFoundException("File not found"));
    }

    public List<Files> getAll() {
        return filesRepository.findAll();
    }

    public List<FilesDTO> getAllDTO() {
        return filesRepository.findAll().stream()
                .map(file -> new FilesDTO(file.getFileName(), file.getFileSize(), file.getFileType(), file.getUploadDate()))
                .toList();
    }

    public Files update(Files fileToUpdate) {
        Files file = filesRepository.findById(fileToUpdate.getFileId()).orElse(new Files());
        file.setFileName(fileToUpdate.getFileName());
        file.setFileSize(fileToUpdate.getFileSize());
        file.setFileType(fileToUpdate.getFileType());
        file.setUploadDate(fileToUpdate.getUploadDate());
        return filesRepository.save(file);
    }

    public void delete(Integer idFile) {
        Files file = filesRepository.findById(idFile).orElseThrow(() -> new EntityNotFoundException("File not found"));
        filesRepository.delete(file);
    }

}
