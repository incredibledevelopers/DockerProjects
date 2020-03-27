package com.ipen.company.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipen.company.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {

	Company findByCompanyName(String companyName);
	
	List<Company> findByIndustry(String industry);
	
	List<Company> findByLocation(String location);
	
	List<Company> findByIndustryAndLocation(String industry, String location);
	
	List<Company> findByStatus(String status);
}
