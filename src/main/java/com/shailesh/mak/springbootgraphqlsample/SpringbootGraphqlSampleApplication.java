package com.shailesh.mak.springbootgraphqlsample;

import com.shailesh.mak.springbootgraphqlsample.model.Address;
import com.shailesh.mak.springbootgraphqlsample.model.Company;
import com.shailesh.mak.springbootgraphqlsample.model.Geo;
import com.shailesh.mak.springbootgraphqlsample.model.User;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.AddressRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.CompanyRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.GeoRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootGraphqlSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGraphqlSampleApplication.class, args);
    }

}
