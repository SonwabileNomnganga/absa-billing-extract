package za.co.absa.africanacity.AbsaBillingExtract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.absa.africanacity.AbsaBillingExtract.domain.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
