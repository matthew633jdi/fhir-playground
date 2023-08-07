package com.example.demo.context;

import ca.uhn.fhir.context.FhirContext;

public class FHIRContext {

    private FHIRContext() {}
    private static class FHIRContextHolder {
        private static final FhirContext INSTANCE = FhirContext.forR4();
    }

    public static FhirContext getInstance() {
        return FHIRContextHolder.INSTANCE;
    }

}
