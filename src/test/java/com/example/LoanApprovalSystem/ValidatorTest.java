package com.example.LoanApprovalSystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.LoanApprovalSystem.utils.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for Validator")

public class ValidatorTest {

  @Test
  @DisplayName("Age validator should throw exception for age below 18")
  public void ageValidator_ageBelow18_shouldThrowException() {
    assertThrows(IllegalArgumentException.class, () -> {
      Validator.ageValidator(17);
    });
  }

  @Test
  @DisplayName("Age validator should pass for age 18 and above")
  public void ageValidator_age18AndAbove_shouldPass() {
    assertDoesNotThrow(() -> {
      Validator.ageValidator(20);
    });
  }

  @Test
  @DisplayName("Score validator should return approval message for score >= 200")
  public void scoreValidator_scoreAbove200_shouldReturnApprovalMessage() {
    String result = Validator.scoreValidator(300);
    assertEquals("Parabéns, o seu score é 300 e seu empréstimo foi aprovado.", result);
  }

  @Test
  @DisplayName("Score validator should throw exception for score < 200")
  public void scoreValidator_scoreBelow200_shouldThrowException() {
    assertThrows(IllegalArgumentException.class, () -> {
      Validator.scoreValidator(151);
    });
  }
}