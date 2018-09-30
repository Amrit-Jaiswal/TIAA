package com.credit.tiaa.repository;

import com.credit.tiaa.model.CreditCardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CCRepository extends JpaRepository<CreditCardDetails, String> {
    List<CreditCardDetails> findByCardNumber(String cardNumber);
}
