package com.example.sau.service;

import com.example.sau.model.CustomerForm;
import com.example.sau.repository.CustomerFormRepo;
import com.example.sau.service.impl.CustomerFormService;
import org.springframework.stereotype.Service;

@Service
public class CustomerFormServiceImpl implements CustomerFormService {
    private final CustomerFormRepo customerFormRepo;

    public CustomerFormServiceImpl(CustomerFormRepo customerFormRepo) {
        this.customerFormRepo = customerFormRepo;
    }

    @Override
    public void addCustomerForm(CustomerForm customerForm) {
        customerFormRepo.save(customerForm);
    }
}
