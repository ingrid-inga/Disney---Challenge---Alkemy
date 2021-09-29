package ar.com.api.disneychallenge.disneychallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.api.disneychallenge.disneychallenge.entities.Staff;
import ar.com.api.disneychallenge.disneychallenge.repos.StaffRepository;

@Service
public class StaffService {

    @Autowired
      StaffRepository repository;

    public void crearStaff(Staff staff) {
        repository.save(staff);
    }
}