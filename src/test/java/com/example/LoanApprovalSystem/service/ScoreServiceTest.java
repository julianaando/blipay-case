package com.example.LoanApprovalSystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.LoanApprovalSystem.model.Score;
import com.example.LoanApprovalSystem.controller.dto.CalculateScoreRequest;
import com.example.LoanApprovalSystem.controller.dto.ScoreResponse;
import com.example.LoanApprovalSystem.repository.ScoreRepository;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for ScoreService")
class ScoreServiceTest {

  @Mock
  private ScoreRepository scoreRepository;

  @InjectMocks
  private ScoreService scoreService;

  private CalculateScoreRequest mockRequest;

  private String cpf;

  @BeforeEach
  public void setup() {
    cpf = "12345678900";

    mockRequest = new CalculateScoreRequest();
    mockRequest.setCpf(cpf);
    mockRequest.setAge(25);
    mockRequest.setName("Juliana");
    mockRequest.setMonthlyIncome(new BigDecimal("5000.00"));
    mockRequest.setCity("Campinas");
  }

  @Test
  @DisplayName("Valid result for method calculateScore")
  public void calculateScore_validInput_shouldReturnValidScore() {

    ScoreService scoreServiceSpy = spy(scoreService);
    doReturn(25.0).when(scoreServiceSpy).getTemperature("Campinas");

    String result = scoreServiceSpy.calculateScore(mockRequest);

    verify(scoreRepository, times(1)).save(any(Score.class));
    assertEquals("Parabéns, o seu score é 237 e seu empréstimo foi aprovado.", result);
  }

  @Test
  @DisplayName("Invalid result fot method getScoresByCpf")
  public void calculateScore_invalidAge_shouldThrowException() {
    mockRequest.setAge(15);

    ScoreService scoreServiceSpy = spy(scoreService);
    doReturn(25.0).when(scoreServiceSpy).getTemperature("Campinas");

    assertThrows(IllegalArgumentException.class, () -> {
      scoreServiceSpy.calculateScore(mockRequest);
    });
  }

  @Test
  @DisplayName("Valid result for method getScoresByCpf")
  public void getScoresByCpf_validCpf_shouldReturnScoreResponse() {
    Score score1 = new Score();
    score1.setCpf(cpf);
    score1.setScore(237);
    score1.setCreatedAt(new Date());

    Score score2 = new Score();
    score2.setCpf(cpf);
    score2.setScore(221);
    score2.setCreatedAt(new Date());

    when(scoreRepository.findByCpf(cpf)).thenReturn(List.of(score1, score2));

    List<ScoreResponse> result = scoreService.getScoresByCpf(cpf);

    assertEquals(2, result.size());
    assertEquals(cpf, result.get(0).getCpf());
    assertEquals(237, result.get(0).getScore());
    assertEquals(cpf, result.get(1).getCpf());
    assertEquals(221, result.get(1).getScore());

    verify(scoreRepository, times(1)).findByCpf(cpf);
  }

  @Test
  @DisplayName("Invalid result for method getScoresByCpf")
  public void getScoresByCpf_invalidCpf_shouldThrowException() {
    when(scoreRepository.findByCpf(anyString())).thenReturn(Collections.emptyList());

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      scoreService.getScoresByCpf(cpf);
    });

    assertEquals("Nenhuma análise de crédito encontrada para o CPF " + cpf, exception.getMessage());

    verify(scoreRepository, times(1)).findByCpf(cpf);
  }
}