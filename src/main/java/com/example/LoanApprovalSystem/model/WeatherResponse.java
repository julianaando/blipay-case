package com.example.LoanApprovalSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
  private Main main;

  public Main getMain() {
    return main;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Main {
    private Double temp;

    public Double getTemp() {
      return temp;
    }

    public void setTemp(Double temp) {
      this.temp = temp;
    }
  }


}
