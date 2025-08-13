package br.com.health_plan_beneficiary.core.models;

import br.com.health_plan_beneficiary.core.dto.DocumentDto;
import br.com.health_plan_beneficiary.core.dto.RecipientRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipient extends BaseEntity
{

    @Id
    private UUID id;
    private String name;
    private String phone;
    private LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "recipient")
    private List<Document> documents = new ArrayList<>();

    public void addDocument(Document document)
    {
        documents.add(document);
        document.setRecipient(this);
    }

    public static Recipient createRecipient(RecipientRequest recipient)
    {
        Recipient createrecipient = new Recipient();
        createrecipient.setId(UUID.randomUUID());
        createrecipient.setName(recipient.name());
        createrecipient.setPhone(recipient.phone());
        createrecipient.setDateOfBirth(recipient.dataBirth());

        for (DocumentDto dto : recipient.documents()) {
            Document document = new Document(dto);
            createrecipient.addDocument(document);
        }

        return createrecipient;
    }

}
