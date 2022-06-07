package com.swm.baseframework.app.member.repository;

import com.swm.baseframework.app.member.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}