package com.myfirstmicroservices.firstjobapp.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRespository extends JpaRepository<Company, Long> {
}
