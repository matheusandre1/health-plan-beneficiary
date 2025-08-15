package br.com.health_plan_beneficiary.core.models;

import br.com.health_plan_beneficiary.core.dto.DocumentDto;
import br.com.health_plan_beneficiary.core.dto.BeneficiaryRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary extends BaseEntity
{

    @Id
    private UUID id;
    private String name;
    private String phone;
    private LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "beneficiary")
    private List<Document> documents = new ArrayList<>();

    public void addDocument(Document document)
    {
        documents.add(document);
        document.setBeneficiary(this);
    }

    public static Beneficiary createBeneficiary(BeneficiaryRequest beneficiary)
    {
        Beneficiary createrecipient = new Beneficiary();
        createrecipient.setId(UUID.randomUUID());
        createrecipient.setName(beneficiary.name());
        createrecipient.setPhone(beneficiary.phone());
        createrecipient.setDateOfBirth(beneficiary.dataBirth());

        for (DocumentDto dto : beneficiary.documents()) {
            Document document = new Document(dto);
            createrecipient.addDocument(document);
        }

        return createrecipient;
    }

}
