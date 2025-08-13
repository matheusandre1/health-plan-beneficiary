package br.com.health_plan_beneficiary.core.dto;

import br.com.health_plan_beneficiary.core.models.Document;

import javax.print.Doc;

public record DocumentDto(String typeDocument,
                          String description) {

    public DocumentDto(Document document)
    {
        this(document.getTypeDocument(),document.getDescription());
    }


}
