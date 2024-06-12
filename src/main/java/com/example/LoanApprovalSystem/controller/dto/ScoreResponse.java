package com.example.LoanApprovalSystem.controller.dto;

import java.util.Date;

public class ScoreResponse {

  private String cpf;
  private Integer score;
  private Date createdAt;

  public ScoreResponse(String cpf, Integer score, Date createdAt) {
    this.cpf = cpf;
    this.score = score;
    this.createdAt = createdAt;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

}
