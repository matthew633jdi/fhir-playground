package com.example.demo.factory;

import com.example.demo.dto.BasicDto;
import org.hl7.fhir.r4.model.Resource;

public interface ResourceFactory {
    default Resource getResource(BasicDto dto) {
        validateDto(dto);
        Resource resource = convertResource(dto);

        return resource;
    }

    Resource convertResource(BasicDto dto);

    void validateDto(BasicDto dto);

    
}
