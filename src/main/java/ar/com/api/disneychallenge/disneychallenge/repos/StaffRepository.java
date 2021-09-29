package ar.com.api.disneychallenge.disneychallenge.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.disneychallenge.disneychallenge.entities.Staff;

@Repository

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    
}
