package com.example.demo.context;

import ca.uhn.fhir.context.FhirContext;

public enum EFHIRContext {
    INSTANCE;

    private FhirContext ctx;

    EFHIRContext() {
        this.ctx = FhirContext.forR4();
    }

    public FhirContext getFHIRContext() {
        return ctx;
    }
}
