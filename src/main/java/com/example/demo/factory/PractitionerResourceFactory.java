package com.example.demo.factory;

import com.example.demo.dto.BasicDto;
import com.example.demo.dto.PractitionerDto;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Resource;

public class PractitionerResourceFactory implements ResourceFactory {
    @Override
    public Resource convertResource(BasicDto dto) {
        PractitionerDto practitionerDto = (PractitionerDto) dto;
        Practitioner practitioner = new Practitioner();
        practitioner.setId(String.valueOf(practitionerDto.getId()));
        HumanName humanName = new HumanName();
        humanName.setText(practitionerDto.getName());
        practitioner.addName(humanName);
        return practitioner;
    }

    @Override
    public void validateDto(BasicDto dto) {
        PractitionerDto practitionerDto = (PractitionerDto) dto;
        if (practitionerDto.getId() == null) {
            throw new IllegalArgumentException("ID required");
        }
    }
}
