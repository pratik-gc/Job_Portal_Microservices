package com.myfirstmicroservices.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company updatedCompany, Long companyId);
    void createCompany(Company company);
}
