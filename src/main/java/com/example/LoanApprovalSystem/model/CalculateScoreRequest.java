package com.example.LoanApprovalSystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CalculateScoreRequest {

  @NotBlank
  private String name;

  @NotNull
  @Positive
  private Integer age;

  @NotBlank
  @Size(min = 11, max = 11)
  private String cpf;

  @NotNull
  @Positive
  private BigDecimal monthlyIncome;

  @NotBlank
  private String city;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public BigDecimal getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(BigDecimal monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
