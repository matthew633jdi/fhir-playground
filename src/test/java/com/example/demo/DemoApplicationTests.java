package com.example.demo;

import ca.uhn.fhir.context.FhirContext;
import com.example.demo.context.EFHIRContext;
import com.example.demo.context.FHIRContext;
import com.example.demo.dto.PatientDto;
import com.example.demo.dto.PractitionerDto;
import com.example.demo.factory.PatientResourceFactory;
import com.example.demo.factory.PractitionerResourceFactory;
import com.example.demo.factory.ResourceFactory;
import com.example.demo.service.ResourceService;
import org.assertj.core.api.Assertions;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Inner class test")
	void checkDuplication(){
		FhirContext instance = FHIRContext.getInstance();
		FhirContext instance1 = FHIRContext.getInstance();
		FhirContext ctx = FhirContext.forR4();

		System.out.println("ctx.getClass() = " + ctx.getClass());
		System.out.println(instance1 == ctx);
		Assertions.assertThat(instance).isSameAs(instance1);
	}

	@Test
	@DisplayName("Enum test")
	void checkDuplEnum() {
		FhirContext context = EFHIRContext.INSTANCE.getFHIRContext();
		FhirContext context1 = EFHIRContext.INSTANCE.getFHIRContext();
		FhirContext ctx = FhirContext.forR4();

		System.out.println(context == ctx);

		Assertions.assertThat(context).isSameAs(context1);
	}

	@Test
	@DisplayName("use FhirContext")
	void useFhirContext() {
		FhirContext context = EFHIRContext.INSTANCE.getFHIRContext();

		Patient patient = new Patient();
		patient.setId("123");

		String patientJson = context.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
		System.out.println(patientJson);
		Assertions.assertThat(patientJson).isNotNull();
	}

	@Test
	@DisplayName("Factory method")
	void patientFactory() {
		// given
		ResourceFactory factory = new PatientResourceFactory();
		PatientDto patientDto = new PatientDto();
		patientDto.setId(1L);
		patientDto.setFamilyName("John");
		patientDto.setGivenName("Cina");
		patientDto.setGender("male");
		patientDto.setBirthDate(LocalDate.of(1985, 12,1));

		// when
		Resource resource = factory.getResource(patientDto);

		FhirContext context = EFHIRContext.INSTANCE.getFHIRContext();
		String resourceJson = context.newJsonParser().setPrettyPrint(true).encodeResourceToString(resource);

		// then
		System.out.println(resourceJson);
		Assertions.assertThat(resourceJson).isNotBlank();
//		Assertions.assertThat(resource).isInstanceOf(Patient.class);
	}

	@Test
	@DisplayName("Practitioner Factory method")
	void practitionerFactory() {
		// given
		ResourceFactory factory = new PractitionerResourceFactory();
		PractitionerDto practitionerDto = new PractitionerDto();
		practitionerDto.setId(2L);
		practitionerDto.setName("Hulk");

		// when
		Resource resource = factory.getResource(practitionerDto);

		FhirContext context = EFHIRContext.INSTANCE.getFHIRContext();
		String resourceJson = context.newJsonParser().setPrettyPrint(true).encodeResourceToString(resource);

		// then
		System.out.println(resourceJson);
		Assertions.assertThat(resourceJson).isNotBlank();
//		Assertions.assertThat(resource).isInstanceOf(Patient.class);
	}

	@Test
	@DisplayName("Patient Service Test")
	void serviceTest() {
		// given
		ResourceFactory factory = new PatientResourceFactory();
		ResourceService service = new ResourceService(factory);

		PatientDto dto = new PatientDto();
		dto.setId(1L);
		dto.setFamilyName("Han");
		dto.setGivenName("Ji woo");
		dto.setGender("male");
		dto.setBirthDate(LocalDate.of(2000, 03,12));

		// when
		String result = service.getResult(dto);
		// then
		System.out.println(result);
		Assertions.assertThat(result).isNotBlank();
	}

	@Test
	@DisplayName("Practitioner Service Test")
	void serviceTest1() {
		// given
		ResourceFactory factory = new PractitionerResourceFactory();
		ResourceService service = new ResourceService(factory);

		PractitionerDto dto = new PractitionerDto();
		dto.setId(1L);
		dto.setName("Lee sul");


		// when
		String result = service.getResult(dto);
		// then
		System.out.println(result);
		Assertions.assertThat(result).isNotBlank();
	}
}
