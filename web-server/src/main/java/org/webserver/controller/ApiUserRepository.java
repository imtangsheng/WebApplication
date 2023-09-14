package org.webserver.controller;
import org.springframework.data.repository.CrudRepository;

public interface ApiUserRepository extends CrudRepository<ApiUserEntity,String> {

}
