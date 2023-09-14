package org.webserver.controller.service;

import org.webserver.controller.ApiUserEntity;

import java.util.List;

public interface ApiUserService
{
    List<ApiUserEntity> findAll();
}
