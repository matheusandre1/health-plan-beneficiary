package br.com.health_plan_beneficiary.controller;


import br.com.health_plan_beneficiary.core.dto.DocumentDto;
import br.com.health_plan_beneficiary.core.dto.BeneficiaryRequest;
import br.com.health_plan_beneficiary.core.dto.BeneficiaryResponse;
import br.com.health_plan_beneficiary.service.RecipientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Faz o Cadastro Do Beneficiário")
    public ResponseEntity<BeneficiaryResponse> createRecipient(@RequestBody @Valid BeneficiaryRequest beneficiaryRequest, UriComponentsBuilder uriBuilder)
    {
        var recipient = recipientService.createBeneficiary(beneficiaryRequest);

        var uri = uriBuilder
                .path("/api/v1/recipients/{id}")
                .buildAndExpand(recipient.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new BeneficiaryResponse(recipient));

    }


    @GetMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca Todos os Beneficiários do Plano de Saúde")
    public List<BeneficiaryResponse> getAllRecipientsRegisters()
    {
        return recipientService.findByBeneficiaryRegisters();
    }

    @GetMapping("/{id}/documents")
    @Transactional
    @ApiResponse(responseCode = "200", description = "No Content")
    @ApiResponse(responseCode = "404", description = "No Found")
    @Operation(summary = "Busca os Documentos Do Beneficário do Plano de saúde por Identificação")
    public List<DocumentDto> listerDocuments(@PathVariable UUID id)
    {
        return recipientService.listenerDocuments(id);
    }

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualiza um Beneficiário")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "404", description = "No Found")
    public ResponseEntity<Void> updateRecipient(@PathVariable UUID id, @Valid BeneficiaryRequest beneficiaryRequest)
    {
        recipientService.updateBeneficiary(id, beneficiaryRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Busca um usuário por ID e Deleta")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<Void> removeRecipient(@PathVariable UUID id)
    {
        recipientService.removeBeneficiary(id);

        return ResponseEntity.noContent().build();
    }


}
