package za.co.ajk.in28min.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//static filtering -> @JsonIgnoreProperties(value={"field2","field3"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;
    private String field2;

    //  static filtering -> @JsonIgnore
    private String field3;

}
