package com.shailesh.mak.springbootgraphqlsample.repositoriees;

import com.shailesh.mak.springbootgraphqlsample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
