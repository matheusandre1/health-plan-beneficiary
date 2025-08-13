package br.com.health_plan_beneficiary.service;

import br.com.health_plan_beneficiary.core.dto.DocumentDto;
import br.com.health_plan_beneficiary.core.dto.RecipientRequest;
import br.com.health_plan_beneficiary.core.dto.RecipientResponse;
import br.com.health_plan_beneficiary.core.models.Document;
import br.com.health_plan_beneficiary.core.models.Recipient;
import br.com.health_plan_beneficiary.repository.RecipientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;

    public Recipient createRecipient(RecipientRequest recipientRequest)
    {
        Recipient recipient = Recipient.createRecipient(recipientRequest);

        return recipientRepository.save(recipient);
    }

    public void updateRecipient(UUID id, RecipientRequest recipientRequestDto)
    {
        var recipientRequest = recipientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipient Not Found"));

        recipientRequest.setName(recipientRequest.getName());
        recipientRequest.setDateOfBirth(recipientRequest.getDateOfBirth());
        recipientRequest.getDocuments().clear();

        for (DocumentDto documentDto : recipientRequestDto.documents())
        {
            Document d = new Document();
            d.setTypeDocument(documentDto.typeDocument());
            d.setDescription(documentDto.description());
            d.setRecipient(recipientRequest);
            recipientRequest.getDocuments().add(d);
        }

        recipientRepository.save(recipientRequest);
    }

    public List<RecipientResponse> findByRecipientRegisters()
    {
        return recipientRepository.findAll().stream().map(RecipientResponse::new).collect(Collectors.toList());
    }

    public List<DocumentDto> listenerDocuments(UUID id)
    {
        Recipient recipient = recipientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Documents Not Found"));

        return recipient.getDocuments().stream().map(r-> new DocumentDto(r.getTypeDocument(),r.getDescription())).toList();
    }

    public void removeRecipient(UUID id)
    {
        var exist = recipientRepository.existsById(id);
        if(!exist)
        {
            throw new EntityNotFoundException("Id not found recipient");
        }

        recipientRepository.deleteById(id);
    }
}
