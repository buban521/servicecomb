package cn.lszz.controller;

import cn.lszz.utils.Person;
import cn.lszz.utils.Result;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestSchema(schemaId = "provider")
@RequestMapping("/")
public class ProviderController {
    @GetMapping("/providerHello")
    public String providerHello(String name) {

        int i = 3 / 0;

        String s = "Hello " + name;
        return s;
    }

    @GetMapping("/providerGetPerson")
    public Result<Person> providerGetPerson(String name, Integer age) {

        Person person = new Person(name, age);

        Result<Person> result = Result.SUCCESS();
        result.setData(person);

        return result;
    }

    @GetMapping("/providerGetPerson2")
    public Person providerGetPerson2(String name, Integer age) {

        Person person = new Person(name, age);

        Result<Person> result = Result.SUCCESS();
        result.setData(person);

        return person;
    }

    @GetMapping("/providerGetPerson3")
    public Result<List<Person>> providerGetPerson3(String name, Integer age) {

        int i = 3 / 0;

        Person person1 = new Person(name, age);
        Person person2 = new Person("lisi", 22);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);

        Result<List<Person>> result = Result.SUCCESS();
        result.setData(list);

        return result;
    }

}
