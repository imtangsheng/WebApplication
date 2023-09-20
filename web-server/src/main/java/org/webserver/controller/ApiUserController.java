package org.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webserver.controller.service.ApiUserService;
import org.webserver.other.MeterHttpClient;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class ApiUserController {

    @Autowired
    private ApiUserService apiUserService;

    @GetMapping("/login")
    public String user_login(String username, String password) {
        System.out.println("username:" +username + ";password:" +password);

        List<ApiUserEntity> users = (List<ApiUserEntity>) apiUserService.findAll();
        System.out.println(users.get(0).getUsername());
        return (users.get(0).getToken());
    }

    @GetMapping("/test")
    public String test() throws IOException {

        MeterHttpClient meter = new MeterHttpClient();
        meter.get_test();
        return meter.get_meter_data_by_http("1","p1");
    }
}
