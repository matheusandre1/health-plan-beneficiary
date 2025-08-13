package br.com.health_plan_beneficiary.controller;


import br.com.health_plan_beneficiary.core.dto.DocumentDto;
import br.com.health_plan_beneficiary.core.dto.RecipientRequest;
import br.com.health_plan_beneficiary.core.dto.RecipientResponse;
import br.com.health_plan_beneficiary.service.RecipientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipients")
public class RecipientController {

    @Autowired
    private RecipientService recipientService;



    @PostMapping
    @Transactional
    public ResponseEntity<RecipientResponse> createRecipient(@RequestBody @Valid RecipientRequest recipientRequest, UriComponentsBuilder uriBuilder)
    {
        var recipient = recipientService.createRecipient(recipientRequest);

        var uri = uriBuilder
                .path("/api/v1/recipients/{id}")
                .buildAndExpand(recipient.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new RecipientResponse(recipient));

    }


    @GetMapping
    @Transactional
    public List<RecipientResponse> getAllRecipientsRegisters()
    {
        return recipientService.findByRecipientRegisters();
    }

    @GetMapping("/{id}/documents")
    @Transactional
    public List<DocumentDto> listerDocuments(@PathVariable UUID id)
    {
        return recipientService.listenerDocuments(id);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> updateRecipient(@PathVariable UUID id, @Valid RecipientRequest recipientRequest)
    {
        recipientService.updateRecipient(id, recipientRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeRecipient(@PathVariable UUID id)
    {
        recipientService.removeRecipient(id);

        return ResponseEntity.notFound().build();
    }


}
