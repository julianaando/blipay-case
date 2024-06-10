package com.example.LoanApprovalSystem.repository;

import com.example.LoanApprovalSystem.model.Score;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
  List<Score> findByCpf(String cpf);
}
