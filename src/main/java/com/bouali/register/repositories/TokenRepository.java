package com.bouali.register.repositories;

import com.bouali.register.models.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  Optional<Token> findByToken(String token);
}
