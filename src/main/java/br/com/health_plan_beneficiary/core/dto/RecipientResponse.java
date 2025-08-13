package br.com.health_plan_beneficiary.core.dto;

import br.com.health_plan_beneficiary.core.models.Recipient;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record RecipientResponse(UUID id,
                                String name,
                                String phone,
                                LocalDate dateBirth,
                                List<DocumentDto> documentDtoList) {

    public RecipientResponse(Recipient recipient)
    {
        this(recipient.getId(),
                recipient.getName(),
                recipient.getPhone(),
                recipient.getDateOfBirth(),
                recipient.getDocuments()
                        .stream()
                        .map(DocumentDto::new)
                        .toList());
    }
}
