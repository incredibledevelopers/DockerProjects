package com.ipen.company.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipen.company.model.Company;
import com.ipen.company.repository.CompanyRepository;

@Service
public class CompanyService implements ICompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company getCompanyById(String companyCode) {
		return companyRepository.findById(companyCode).get();
	}
	
	@Override
	public List<Company> getAllCompanies() {
		List<Company> list = new ArrayList<Company>();
		companyRepository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public List<Company> getCompaniesByIndustry(String industry) {
		List<Company> list = new ArrayList<Company>();
		companyRepository.findByIndustry(industry).forEach(list::add);
		return list;
	}
	
	@Override
	public List<Company> getCompaniesByLocation(String location) {
		List<Company> list = new ArrayList<Company>();
		companyRepository.findByLocation(location).forEach(list::add);
		return list;
	}

	@Override
	public List<Company> getCompaniesByIndustryAndLocation(String industry, String location) {
		List<Company> list = new ArrayList<Company>();
		companyRepository.findByIndustryAndLocation(industry, location).forEach(list::add);
		return list;
	}
	
	@Override
	public List<Company> getCompaniesByStatus(String status) {
		List<Company> list = new ArrayList<Company>();
		companyRepository.findByStatus(status).forEach(list::add);
		return list;
	}

	@Override
	public Company createCompany(Company company) {
		company.setCreatedDate(new Date());
		company.setStatus("New");
		return companyRepository.save(company);
	}

	@Override
	public void updateCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public boolean companyExist(String companyName) {
		Company company = companyRepository.findByCompanyName(companyName);
		if (company == null)
			return false;
		else
			return true;
	}
}