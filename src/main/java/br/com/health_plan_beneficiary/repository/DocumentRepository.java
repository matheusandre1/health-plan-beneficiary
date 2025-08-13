package br.com.health_plan_beneficiary.repository;

import br.com.health_plan_beneficiary.core.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
