package com.smartcareer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcareer.entities.Contactmail;

public interface ContactJpaRepository extends JpaRepository<Contactmail, Integer> {

}
