package com.shailesh.mak.springbootgraphqlsample.services;

import antlr.ASTNULLType;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shailesh.mak.springbootgraphqlsample.model.Address;
import com.shailesh.mak.springbootgraphqlsample.model.Company;
import com.shailesh.mak.springbootgraphqlsample.model.Geo;
import com.shailesh.mak.springbootgraphqlsample.model.User;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.AddressRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.CompanyRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.GeoRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private GeoRepository geoRepository;
    @Autowired
    private CompanyRepository companyRepository;


    public List<User> users(){
        return userRepository.findAll();
    }

    public List<Address> addresses(){
        return addressRepository.findAll();
    }

    public List<Geo> geos(){
        return geoRepository.findAll();
    }

    public List<Company> companies(){
        return companyRepository.findAll();
    }

    public User user(final Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
