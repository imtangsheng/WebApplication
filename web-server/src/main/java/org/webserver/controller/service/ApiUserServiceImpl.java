package org.webserver.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webserver.controller.ApiUserEntity;
import org.webserver.controller.ApiUserRepository;

import java.util.List;

@Service
public class ApiUserServiceImpl implements ApiUserService {

    @Autowired
    private ApiUserRepository repository;
    @Override
    public List<ApiUserEntity> findAll() {
        return (List<ApiUserEntity>) repository.findAll();
    }
}
