package br.com.health_plan_beneficiary.core.dto;

import br.com.health_plan_beneficiary.core.models.Beneficiary;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record BeneficiaryRequest(@NotNull String name,
                                 @NotNull String phone,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDate dataBirth,
                                 List<DocumentDto> documents) {



    public BeneficiaryRequest(Beneficiary beneficiary) {
        this(
                beneficiary.getName(),
                beneficiary.getPhone(),
                beneficiary.getDateOfBirth(),
                beneficiary.getDocuments()
                        .stream()
                        .map(DocumentDto::new)
                        .toList()
        );
    }

}
