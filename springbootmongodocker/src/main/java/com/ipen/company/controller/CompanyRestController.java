package com.ipen.company.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipen.company.exception.RecordNotFoundException;
import com.ipen.company.model.Company;
import com.ipen.company.service.ICompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/v1")
@Api(value="company")

@ComponentScan(basePackages = "com.ipen.company")
public class CompanyRestController {

	public static final Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

	@Autowired
	private ICompanyService companyService;

	@ApiOperation(value = "Find a company by ID")
	@GetMapping(value = "company/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") String id) {
		logger.info("Fetching Company with id {}", id);

		Company company = companyService.getCompanyById(id);

		if (company == null) {
			logger.error("Company with id {} not found.", id);
			throw new RecordNotFoundException("Company with id " + id + " not found");
		}

		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}
	
	@ApiOperation(value = "View a list of available companies", response = List.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		}
	)
	@GetMapping(value = "company", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Company>> getAllCompanies() {
		logger.info("Fetching All Companies");

		List<Company> list = companyService.getAllCompanies();
		return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "View a list of available companies by location", response = List.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		}
	)
	@GetMapping(value = "company/findByLocation/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Company>> getAllCompaniesByLocation(@PathVariable("location") String location) {
		logger.info("Fetching All Companies by location");

		List<Company> list = companyService.getCompaniesByLocation(location);
		return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "View a list of available companies by industry", response = List.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		}
	)
	@GetMapping(value = "company/findByIndustry/{industry}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Company>> getAllCompaniesByIndustry(@PathVariable("industry") String industry) {
		logger.info("Fetching All Companies by industry");

		List<Company> list = companyService.getCompaniesByIndustry(industry);
		return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "View a list of available companies by status", response = List.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		}
	)
	@GetMapping(value = "company/findByStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Company>> getAllCompaniesByStatus(@PathVariable("status") String status) {
		logger.info("Fetching All Companies by status");

		List<Company> list = companyService.getCompaniesByStatus(status);
		return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "View a list of available companies by industry and location", response = List.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		}
	)
	@GetMapping(value = "company/findByIndustryAndlocation/{industry}/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Company>> getAllCompaniesByIndustryAndLocation(
			@PathVariable("industry") String industry, @PathVariable("location") String location) {
		logger.info("Fetching All Companies by industry and location");

		List<Company> list = companyService.getCompaniesByIndustryAndLocation(industry, location);
		return new ResponseEntity<List<Company>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "Add a new company to the concept")
	@PostMapping(value = "company", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company, UriComponentsBuilder builder) {
		logger.info("Add Company : {}", company);

		boolean flag = companyService.companyExist(company.getCompanyName());

		if (flag == true) {
			logger.error("Unable to add. A Company with name {} already exist", company.getCompanyName());
			throw new RecordNotFoundException("Unable to add. A Company with name " + company.getCompanyName() + " already exist.");
		}
		
		Company saveCompany = companyService.createCompany(company);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/get/{id}").buildAndExpand(saveCompany.getId()).toUri());
		return new ResponseEntity<Company>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update an existing company")
	@PutMapping(value = "company/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> updateCompany(@PathVariable("id") String id, @Valid @RequestBody Company company) {
		logger.info("Updating Company with id {}", id);

		Company currentCompany = companyService.getCompanyById(id);

		if (currentCompany == null) {
			logger.error("Unable to update. Company with id {} not found.", id);
			throw new RecordNotFoundException("Unable to upate. Company with id " + id + " not found.");
		}

		//currentCompany.setConceptId(company.getConceptId());
		currentCompany.setCompanyName(company.getCompanyName());
		currentCompany.setIndustry(company.getIndustry());
		currentCompany.setLocation(company.getLocation());
		currentCompany.setDescription(company.getDescription());
		currentCompany.setAddress(company.getAddress());
		currentCompany.setOpeningStock(company.getOpeningStock());
		currentCompany.setTaxYear(company.getTaxYear());
		currentCompany.setStatus(company.getStatus());
		companyService.updateCompany(currentCompany);

		return new ResponseEntity<Company>(currentCompany, HttpStatus.OK);
	}
}