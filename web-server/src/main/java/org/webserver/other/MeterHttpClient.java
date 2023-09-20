package org.webserver.other;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import javassist.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import sun.security.krb5.internal.crypto.EType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MeterHttpClient {
    private JSONObject configMeter;
    String configFile = "config/MeterSetting.json";
    String meterBaseUrl = "http://192.168.1.6:8000";
    WebClient meterClient;

    JSONObject meterHttpRequestJson;
    JSONObject meterHttpResponseJson;
    class MeterRequest {
        String meter_code;String point;String img_url;String data;
        public MeterRequest(){}
        public MeterRequest(String meter_code,String point,String img_url,String data){
            this.meter_code = meter_code;
            this.point = point;
            this.img_url = img_url;
            this.data = data;
        }
    }
    class MeterResponse {
        boolean status;String code;String message;Object data;
    }

    public MeterHttpClient() throws IOException {
        String jsonString = "";
        ClassPathResource  classPathResource = new ClassPathResource(configFile);
        if (classPathResource.exists()){
            configMeter = JSON.parseObject(classPathResource.getInputStream(), StandardCharsets.UTF_8,JSONObject.class,
                Feature.AutoCloseSource,
                Feature.AllowComment,
                Feature.AllowSingleQuotes,
                Feature.UseBigDecimal
            );
            System.out.println(configMeter);
        }else {
            throw new IOException("config file not found");
        }
//        System.out.println(org.springframework.core.SpringVersion.getVersion());
        meterClient = WebClient.create(meterBaseUrl);
//        get_test();

    }



    public void get_test(){
        Mono<String> testMono = meterClient.get()
            .uri("/other/test")
            .retrieve()
            .bodyToMono(String.class);
        String test = testMono.block();
        System.out.println(test);
    }
    public String get_code(String code){
        return (String) configMeter.getJSONObject("code").get(code);
    }

    public String get_meter_data_by_http(String meter_code,String point){
        System.out.println("code: " + meter_code + ";point: " + point);
        String url = ("/other/meter");
        JSONObject postData = new JSONObject();
        postData.put("meter_code",meter_code);
        postData.put("point",point);
        postData.put("img_url","");
        postData.put("data","");
        System.out.println(postData);

        Mono<JSONObject> testMono = meterClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(postData)
            .retrieve()
            .bodyToMono(JSONObject.class);
        JSONObject test = testMono.block();
        if (test != null) {
            System.out.println(test);
            if(test.get("status").equals(true)){
                System.out.println(test.get("data").getClass().getSimpleName());
                System.out.println(test.getJSONObject("data"));
                JSONObject data = test.getJSONObject("data");
                System.out.println(data);

                if (Objects.equals(meter_code, data.get("meter_code").toString()) && Objects.equals(point, data.get("point").toString())){
                    System.out.println(data.getJSONObject("data"));

                    JSONObject data_data = data.getJSONObject("data");
                    System.out.println(data_data);
                    return data_data.toString();
                }
                System.out.println(meter_code+ "=="+ data.get("meter_code") + "&&"+ point+ "=="+ data.get("point"));
                return "meter_code is error。请求参数和返回不一样";
            }
            return "status is false.识别失败";
        }
        return "null。数据请求失败";
    }

}
