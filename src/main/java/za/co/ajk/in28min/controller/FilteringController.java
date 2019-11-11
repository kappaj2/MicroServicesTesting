package za.co.ajk.in28min.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ajk.in28min.model.SomeBean;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        //  dynamic filter fields2 and field3
        SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(generateDynamicFilter("field1", "field2"));
        return mappingJacksonValue;
    }


    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveSomeBeanList() {
        //  dynamic filter fields2 and field3
        List<SomeBean> someBeanList = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value11", "Value21", "Value31"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanList);
        mappingJacksonValue.setFilters(generateDynamicFilter("field2", "field3"));
        return mappingJacksonValue;
    }

    private FilterProvider generateDynamicFilter(String field1, String field2) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field1, field2);
        return new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
    }

}
