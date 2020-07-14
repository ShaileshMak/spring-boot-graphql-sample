package com.shailesh.mak.springbootgraphqlsample.repositoriees;

import com.shailesh.mak.springbootgraphqlsample.model.Geo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends JpaRepository<Geo, Long> {
}
