package com.example.LoanApprovalSystem.controller;

import com.example.LoanApprovalSystem.model.CalculateScoreRequest;
import com.example.LoanApprovalSystem.model.ScoreResponse;
import com.example.LoanApprovalSystem.service.ScoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {

  @Autowired
  private ScoreService scoreService;

  @PostMapping("/calculate")
  public ResponseEntity<String> calculateScore(@RequestBody CalculateScoreRequest request) {
    String score = scoreService.calculateScore(request);

    if (score != null) {
      return ResponseEntity.ok(score);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<List<ScoreResponse>> getScoreByCpf(@PathVariable String cpf) {
    List<ScoreResponse> scores = scoreService.getScoresByCpf(cpf);
    if (scores != null && !scores.isEmpty()) {
      return ResponseEntity.ok(scores);
    } else {
      return ResponseEntity.noContent().build();
    }
  }
}
