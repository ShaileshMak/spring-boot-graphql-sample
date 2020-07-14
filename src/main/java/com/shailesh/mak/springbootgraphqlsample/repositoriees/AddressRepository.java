package com.shailesh.mak.springbootgraphqlsample.repositoriees;

import com.shailesh.mak.springbootgraphqlsample.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
