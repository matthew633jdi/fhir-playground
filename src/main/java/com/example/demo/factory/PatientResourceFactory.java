package com.example.demo.factory;

import com.example.demo.dto.BasicDto;
import com.example.demo.dto.PatientDto;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;

import java.sql.Date;
import java.time.LocalDate;

public class PatientResourceFactory implements ResourceFactory {

    @Override
    public Resource convertResource(BasicDto dto) {
        PatientDto patientDto = (PatientDto) dto;
        Patient patient = new Patient();
        patient.setId(String.valueOf(patientDto.getId()));
        HumanName humanName = new HumanName();
        humanName.setFamily(patientDto.getFamilyName());
        humanName.addGiven(patientDto.getGivenName());
        patient.addName(humanName);
        patient.setGender(Enumerations.AdministrativeGender.fromCode(patientDto.getGender()));
        LocalDate birthDate = patientDto.getBirthDate();
        patient.setBirthDate(Date.valueOf(birthDate));
        return patient;
    }

    @Override
    public void validateDto(BasicDto dto) {
        PatientDto patientDto = (PatientDto) dto;
        if (patientDto.getBirthDate() == null) {
            throw new IllegalArgumentException("생년월일 필수입니다.");
        }
    }
}
