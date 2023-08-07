package com.example.demo.service;

import ca.uhn.fhir.context.FhirContext;
import com.example.demo.context.EFHIRContext;
import com.example.demo.dto.BasicDto;
import com.example.demo.factory.ResourceFactory;
import org.hl7.fhir.r4.model.Resource;

public class ResourceService {
    private final ResourceFactory resourceFactory;

    public ResourceService(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }

    public String getResult(BasicDto dto) {
        Resource resource = resourceFactory.getResource(dto);
        return parseResourceToJson(resource);
    }

    private static String parseResourceToJson(Resource resource) {
        FhirContext context = EFHIRContext.INSTANCE.getFHIRContext();
        return context.newJsonParser().setPrettyPrint(true).encodeResourceToString(resource);
    }
}
