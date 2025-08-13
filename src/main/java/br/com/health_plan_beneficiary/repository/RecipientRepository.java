package br.com.health_plan_beneficiary.repository;

import br.com.health_plan_beneficiary.core.models.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
}
