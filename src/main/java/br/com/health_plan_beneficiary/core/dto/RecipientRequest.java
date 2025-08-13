package br.com.health_plan_beneficiary.core.dto;

import br.com.health_plan_beneficiary.core.models.Recipient;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record RecipientRequest(@NotNull String name,
                               @NotNull String phone,
                               @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDate dataBirth,
                               List<DocumentDto> documents) {



    public RecipientRequest(Recipient recipient) {
        this(
                recipient.getName(),
                recipient.getPhone(),
                recipient.getDateOfBirth(),
                recipient.getDocuments()
                        .stream()
                        .map(DocumentDto::new)
                        .toList()
        );
    }

}
