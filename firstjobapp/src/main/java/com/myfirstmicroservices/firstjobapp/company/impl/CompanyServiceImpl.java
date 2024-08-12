package com.myfirstmicroservices.firstjobapp.company.impl;

import com.myfirstmicroservices.firstjobapp.company.Company;
import com.myfirstmicroservices.firstjobapp.company.CompanyRespository;
import com.myfirstmicroservices.firstjobapp.company.CompanyService;
import com.myfirstmicroservices.firstjobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRespository companyRespository;

    public CompanyServiceImpl(CompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRespository.findAll();
    }

    @Override
    public boolean updateCompany(Company updatedCompany, Long companyId) {

        Optional<Company> companyOptional = companyRespository.findById(companyId);

        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            company.setJobs(updatedCompany.getJobs());
            companyRespository.save(company);
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRespository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long companyId) {
        if (companyRespository.existsById(companyId)) {
            companyRespository.deleteById(companyId);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRespository.findById(companyId).orElse(null);
    }
}
