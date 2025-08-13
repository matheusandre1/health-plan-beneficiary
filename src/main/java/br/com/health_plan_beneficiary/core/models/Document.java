package br.com.health_plan_beneficiary.core.models;

import br.com.health_plan_beneficiary.core.dto.DocumentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeDocument;
    private String description;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    public Document(DocumentDto documentDto)
    {
        setTypeDocument(documentDto.typeDocument());
        setDescription(documentDto.description());
    }
}
