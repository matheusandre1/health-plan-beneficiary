package br.com.health_plan_beneficiary.core.dto;

import br.com.health_plan_beneficiary.core.models.Beneficiary;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record BeneficiaryResponse(UUID id,
                                  String name,
                                  String phone,
                                  LocalDate dateBirth,
                                  List<DocumentDto> documentDtoList) {

    public BeneficiaryResponse(Beneficiary beneficiary)
    {
        this(beneficiary.getId(),
                beneficiary.getName(),
                beneficiary.getPhone(),
                beneficiary.getDateOfBirth(),
                beneficiary.getDocuments()
                        .stream()
                        .map(DocumentDto::new)
                        .toList());
    }
}
