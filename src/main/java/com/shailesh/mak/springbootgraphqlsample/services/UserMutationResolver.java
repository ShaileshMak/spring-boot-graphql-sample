package com.shailesh.mak.springbootgraphqlsample.services;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.shailesh.mak.springbootgraphqlsample.model.*;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.AddressRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.CompanyRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.GeoRepository;
import com.shailesh.mak.springbootgraphqlsample.repositoriees.UserRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver, GraphQLSubscriptionResolver {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GeoRepository geoRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private ConcurrentHashMap<Long, FluxSink<User>> subscribers = new ConcurrentHashMap<>();

    public User addUser(final UserRequest userRequest) {
        Geo geo = new Geo();
        updateGeo(userRequest, geo);
        geoRepository.save(geo);

        Address address = new Address();
        updateAddress(userRequest, geo, address);
        addressRepository.save(address);

        Company company = new Company();
        updateCompany(userRequest, company);
        companyRepository.save(company);

        User user = new User();
        updateUser(userRequest, address, company, user);
        userRepository.save(user);

        return user;
    }

    public User updateUserForId(final Long userId, final UserRequest userRequest) {
        User user = userRepository.findById(userId).orElse(null);
        Address address = user.getAddress();
        Geo geo = address.getGeo();
        Company company = user.getCompany();

        updateGeo(userRequest, geo);
        geoRepository.save(geo);

        updateAddress(userRequest, geo, address);
        addressRepository.save(address);

        updateCompany(userRequest, company);
        companyRepository.save(company);

        updateUser(userRequest, address, company, user);
        userRepository.save(user);

        if(subscribers.get(userId)!=null){
            subscribers.get(userId).next(user);
        }

        return user;
    }

    public String deleteUserForId(final Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            userRepository.deleteById(userId);
            return "Deleted Successfully";
        } else {
            return "User not found";
        }
    }

    public Publisher<User> onUserUpdate(final Long id){
        return Flux.create(subscriber-> subscribers.put(id,subscriber.onDispose(()->subscribers.remove(id,subscriber))), FluxSink.OverflowStrategy.LATEST);
    }

    private void updateUser(final UserRequest userRequest, final Address address, final Company company, final User user) {
        user.setName(userRequest.getName());
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setPhone(userRequest.getPhone());
        user.setWebsite(userRequest.getWebsite());
        user.setAddress(address);
        user.setCompany(company);
    }

    private void updateCompany(final UserRequest userRequest, final Company company) {
        company.setName(userRequest.getCompanyName());
        company.setCatchPhrase(userRequest.getCatchPhrase());
        company.setBs(userRequest.getBs());
    }

    private void updateAddress(final UserRequest userRequest, final Geo geo, final Address address) {
        address.setStreet(userRequest.getStreet());
        address.setSuit(userRequest.getSuit());
        address.setCity(userRequest.getCity());
        address.setZipcode(userRequest.getZipcode());
        address.setGeo(geo);
    }

    private void updateGeo(final UserRequest userRequest, final Geo geo) {
        geo.setLat(userRequest.getLat());
        geo.setLng(userRequest.getLng());
    }
}
