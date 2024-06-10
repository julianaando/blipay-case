package com.example.LoanApprovalSystem.service;

import com.example.LoanApprovalSystem.model.CalculateScoreRequest;
import com.example.LoanApprovalSystem.model.Score;
import com.example.LoanApprovalSystem.model.ScoreResponse;
import com.example.LoanApprovalSystem.model.WeatherResponse;
import com.example.LoanApprovalSystem.repository.ScoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreService {
  @Value("${openweather.api.key}")
  private String apiKey;

  @Autowired
  private ScoreRepository scoreRepository;

  public String calculateScore(CalculateScoreRequest request) {
    Integer age = request.getAge();
    BigDecimal monthlyIncome = request.getMonthlyIncome();
    String city = request.getCity();
    String cpf = request.getCpf();

    Double temperature = getTemperature(city);

    if (age >= 18) {
      int scoreValue = (int) ((age * 0.5) + ((monthlyIncome.doubleValue() / 100) * 2) + (temperature * 5));
      Score score = new Score();
      score.setCpf(cpf);
      score.setScore(scoreValue);
      score.setCreatedAt(new Date());
      scoreRepository.save(score);

      if (scoreValue >= 200) {
        return "Parabéns, o seu score é " + scoreValue + " e seu empréstimo foi aprovado.";
      } else {
        return "Infelizmente, o seu score é " + scoreValue + " e seu empréstimo foi negado.";
      }
    } else {
      return "Sua idade deve ser maior ou igual a 18 anos.";
    }
  }

  /*private Double getTemperature(String city) {
    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest fetchApi = HttpRequest.newBuilder()
        .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey))
        .build();

      HttpResponse<String> response = client.send(fetchApi, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        String responseBody = response.body();
        String temp = responseBody.split("\"temp\":")[1].split(",")[0];
        return kelvinToCelsius(Double.parseDouble(temp));
      } else {
        System.out.println("Erro na resposta da API: " + response.statusCode());
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar temperatura da cidade", e);
    }
  }*/

  private Double getTemperature(String city) {
    try {
      RestTemplate restTemplate = new RestTemplate();
      String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
      String response = restTemplate.getForObject(url, String.class);

      if (response == null || response.contains("404")) {
        throw new RuntimeException("A cidade " + city +  " não foi encontrada.");
      }

      ObjectMapper mapper = new ObjectMapper();
      WeatherResponse weatherResponse = mapper.readValue(response, WeatherResponse.class);
      Double tempKelvin = weatherResponse.getMain().getTemp();

      return kelvinToCelsius(tempKelvin);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar temperatura da cidade", e);
    }
  }

  private Double kelvinToCelsius(Double tempKelvin) {
    return tempKelvin - 273.15;
  }

  //criar endpoint que busca todas as análises de crédito por cpf
  public List<ScoreResponse> getScoresByCpf(String cpf) {
    List<Score> scores = scoreRepository.findByCpf(cpf);

    if (scores.isEmpty()) {
      throw new RuntimeException("Nenhuma análise de crédito encontrada para o CPF " + cpf);
    }
    return scores.stream()
      .map(score -> new ScoreResponse(score.getCpf(), score.getScore(), score.getCreatedAt()))
      .collect(Collectors.toList());
  }
}
