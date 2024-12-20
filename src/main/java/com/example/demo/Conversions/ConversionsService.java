package com.example.demo.Conversions;

import com.example.demo.Files.FilesRepository;
import com.example.demo.Files.FilesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversionsService {

    private final ConversionsRepository conversionsRepository;
    private final FilesService filesService;
    private final FilesRepository filesRepository;

    public Conversions create(ConversionsDTO conversionsDTO) {

        return conversionsRepository.save(Conversions.builder()
                .file(filesRepository.findByFileName(conversionsDTO.getFileName()).orElseThrow(() -> new EntityNotFoundException("File not found")))
                .sourceFormat(conversionsDTO.getSourceFormat())
                .targetFormat(conversionsDTO.getTargetFormat())
                .status(conversionsDTO.getStatus())
                .conversionDate(conversionsDTO.getConversionDate())
                .build());
    }

    public Conversions getOne(Integer idConversion) {
        return conversionsRepository.findById(idConversion).orElseThrow(() -> new EntityNotFoundException("Conversion not found"));
    }

    public List<Conversions> getAll() {
        return conversionsRepository.findAll();
    }

    public List<ConversionsDTO> getAllDTO() {
        return conversionsRepository.findAll().stream()
                .map(conversion -> new ConversionsDTO(
                        conversion.getFile().getFileName(),
                        conversion.getSourceFormat(),
                        conversion.getTargetFormat(),
                        conversion.getStatus(),
                        conversion.getConversionDate()
                ))
                .toList();
    }

    public Conversions update(Conversions conversionToUpdate) {
        Conversions conversion = conversionsRepository.findById(conversionToUpdate.getConversionId()).orElse(new Conversions());
        conversion.setSourceFormat(conversionToUpdate.getSourceFormat());
        conversion.setTargetFormat(conversionToUpdate.getTargetFormat());
        conversion.setStatus(conversionToUpdate.getStatus());
        conversion.setConversionDate(conversionToUpdate.getConversionDate());
        return conversionsRepository.save(conversion);
    }

    public void delete(Integer idConversion) throws Exception {
        Conversions conversion = conversionsRepository.findById(idConversion).orElseThrow(() -> new EntityNotFoundException("Conversion not found"));
        conversionsRepository.delete(conversion);
    }
}
