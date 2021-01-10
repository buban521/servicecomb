package cn.lszz.controller;

import cn.lszz.utils.Person;
import cn.lszz.utils.Result;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestSchema(schemaId = "consumer")
@RequestMapping("/")
public class ConsumerController {

    RestTemplate restTemplate = RestTemplateBuilder.create();

    @GetMapping("/consumerHello")
    public String consumerHello(String name) {

        int i = 3 / 0;

        return "Hello " + name;
    }

    @GetMapping("/consumerGetPerson")
    public String consumerGetPerson(String name, Integer age) {

//        String providerUrl = "cse://provider/providerGetPerson?name=" + name + "&age=" + age + "";
        String providerUrl = "cse://provider/providerGetPerson?name={name}&age={age}";
//        String providerUrl = "http://localhost:8080/providerGetPerson?name={name}&age={age}";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        map.put("age", age);
        ResponseEntity<Object> entity = restTemplate.getForEntity(providerUrl, Object.class, map);


//        ParameterizedTypeReference<Result> typeReference = new ParameterizedTypeReference<Result>() { };


//        ResponseEntity<Result> entity = restTemplate.exchange(providerUrl, HttpMethod.GET, null, typeReference);



        return "Hello world";
    }

    @GetMapping("/consumerGetPerson2")
    public String consumerGetPerson2(String name, Integer age) {

        String providerUrl = "cse://provider/providerGetPerson2?name=" + name + "&age=" + age + "";
//        String providerUrl = "cse://provider/providerGetPerson2?name={name}&age={age}";
//        String providerUrl = "cse://provider/providerGetPerson2?name={name}&age={age}";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        map.put("age", age);
//        ResponseEntity<String> entity = restTemplate.getForEntity(providerUrl, String.class, map);


        ParameterizedTypeReference<Person> typeReference = new ParameterizedTypeReference<Person>() { };


        ResponseEntity<Person> entity = restTemplate.exchange(providerUrl, HttpMethod.GET, null, typeReference);



        return "Hello world";
    }

    @GetMapping("/consumerGetPerson3")
    public Result<List<Person>> consumerGetPerson3(String name, Integer age) {

        String providerUrl = "cse://provider/providerGetPerson3?name=" + name + "&age=" + age + "";
//        String providerUrl = "cse://provider/providerGetPerson2?name={name}&age={age}";
//        String providerUrl = "cse://provider/providerGetPerson2?name={name}&age={age}";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        map.put("age", age);

        ResponseEntity<Object> entity = null;
        Result<List<Person>> persons = Result.FAIL();
        try {
            entity = restTemplate.getForEntity(providerUrl, Object.class, map);
            if (entity.getStatusCode().is2xxSuccessful()) {
                Object body = entity.getBody();
                if (body != null) {
                    persons = (Result<List<Person>>) body;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            InvocationException invocationException = (InvocationException) e;
            Map<String, Object> errorData = (Map<String, Object>) invocationException.getErrorData();
            Integer code = Integer.parseInt(errorData.get("code").toString());
            String message = errorData.get("message").toString();
            persons = new Result<>(code, message, null);
            return persons;

        }

        return persons;
    }
}
