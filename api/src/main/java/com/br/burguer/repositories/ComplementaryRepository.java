package com.br.burguer.repositories;

import com.br.burguer.modules.Complementary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplementaryRepository extends JpaRepository<Complementary, Long> {
}
