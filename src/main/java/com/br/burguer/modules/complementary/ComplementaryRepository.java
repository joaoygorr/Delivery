package com.br.burguer.modules.complementary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplementaryRepository extends JpaRepository<Complementary, Long> {
}
