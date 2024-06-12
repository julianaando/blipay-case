package com.example.LoanApprovalSystem.utils;

public class Validator {

  public static Integer ageValidator(Integer age) {
    if (age < 18) {
      throw new IllegalArgumentException("Sua idade deve ser maior ou igual a 18 anos.");
    } else {
      return age;
    }
  }

  public static String scoreValidator(Integer score) {
    if (score >= 200) {
      return "Parabéns, o seu score é " + score + " e seu empréstimo foi aprovado.";
    } else {
      throw new IllegalArgumentException("Infelizmente, o seu score é " + score + " e seu empréstimo foi negado.");
    }
  }
}
