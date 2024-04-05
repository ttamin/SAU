package com.example.sau.repository;

import com.example.sau.model.CustomerForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFormRepo extends JpaRepository<CustomerForm, Long> {

}
