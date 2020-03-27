package com.ipen.company.model;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "companies")
@TypeAlias("company")
public class Company {
	
	public static class IdComparator implements Comparator<Company>
	{
	    public int compare(Company p1, Company p2)
	    {
	        int id1 = Integer.parseInt(p1.getId());
	        int id2 = Integer.parseInt(p2.getId());

	        if (id1 == id2)
	            return 0;
	        else if (id1 > id2)
	            return 1;
	        else
	            return -1;
	    }
	}

	@Id
    public String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Field("company_name")
	@NotEmpty(message = "Company name must not be empty")
	private String companyName;
	
	@Field("description")
	@NotEmpty(message = "Description must not be empty")
	private String description;
	
	@Field("industry")
	@NotEmpty(message = "Industry must not be empty")
	private String industry;
	
	@Field("location")
	@NotEmpty(message = "Location must not be empty")
	private String location;
	
	@Field("address")
	private String address;
	
	@Field("opening_stock")
	private Float openingStock;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(Float openingStock) {
		this.openingStock = openingStock;
	}

	public String getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Field("tax_year")
	private String taxYear;
	
	@Field("status")
	private String status;
	
	@Field("created_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;

	@Field("last_modified_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date lastModifiedDate;
	
	@Override
    public String toString() {
        return "Company: [ID =" + id + ", companyName=" + companyName + ", description=" + description + ", industry=" + industry + ", location= "+ location + "]";
    }
}