package com.excilys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
