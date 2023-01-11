package com.github.pawelkorniak;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface JpaService extends JpaRepository<Event,Long> {
}
