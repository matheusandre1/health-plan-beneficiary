package br.com.health_plan_beneficiary.service;

import br.com.health_plan_beneficiary.core.dto.DocumentDto;
import br.com.health_plan_beneficiary.core.dto.BeneficiaryRequest;
import br.com.health_plan_beneficiary.core.dto.BeneficiaryResponse;
import br.com.health_plan_beneficiary.core.models.Document;
import br.com.health_plan_beneficiary.core.models.Beneficiary;
import br.com.health_plan_beneficiary.repository.BeneficiaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipientService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    public Beneficiary createBeneficiary(BeneficiaryRequest beneficiaryRequest)
    {
        Beneficiary beneficiary = Beneficiary.createBeneficiary(beneficiaryRequest);

        return beneficiaryRepository.save(beneficiary);
    }

    public void updateBeneficiary(UUID id, BeneficiaryRequest beneficiaryRequestDto)
    {
        var recipientRequest = beneficiaryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipient Not Found"));

        recipientRequest.setName(recipientRequest.getName());
        recipientRequest.setDateOfBirth(recipientRequest.getDateOfBirth());
        recipientRequest.getDocuments().clear();

        for (DocumentDto documentDto : beneficiaryRequestDto.documents())
        {
            Document d = new Document();
            d.setTypeDocument(documentDto.typeDocument());
            d.setDescription(documentDto.description());
            d.setBeneficiary(recipientRequest);
            recipientRequest.getDocuments().add(d);
        }

        beneficiaryRepository.save(recipientRequest);
    }

    public List<BeneficiaryResponse> findByBeneficiaryRegisters()
    {
        return beneficiaryRepository.findAll().stream().map(BeneficiaryResponse::new).collect(Collectors.toList());
    }

    public List<DocumentDto> listenerDocuments(UUID id)
    {
        Beneficiary beneficiary = beneficiaryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Documents Not Found"));

        return beneficiary.getDocuments().stream().map(r-> new DocumentDto(r.getTypeDocument(),r.getDescription())).toList();
    }

    public void removeBeneficiary(UUID id)
    {
        var exist = beneficiaryRepository.existsById(id);
        if(!exist)
        {
            throw new EntityNotFoundException("Id not found recipient");
        }

        beneficiaryRepository.deleteById(id);
    }
}
