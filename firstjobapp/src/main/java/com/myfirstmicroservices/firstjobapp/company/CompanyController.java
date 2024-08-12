package com.myfirstmicroservices.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompany(@RequestBody Company updatedCompany, @PathVariable Long companyId){
        companyService.updateCompany(updatedCompany, companyId);
        return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteComapny(@PathVariable Long companyId){
        boolean isDeleted = companyService.deleteCompanyById(companyId);
        if (isDeleted)
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Company could not be deleted!!!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId){
        Company company = companyService.getCompanyById(companyId);
        if (company != null)
            return new ResponseEntity<>(company, HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
