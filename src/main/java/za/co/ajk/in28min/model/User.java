package za.co.ajk.in28min.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
//@ApiModel(description = "All details about the user")
public class User {

    @Id
    @GeneratedValue
    private Integer Id;

    @Size(min = 2, message = "Name should have at least two characters")
//    @ApiModelProperty("Name should be at least two characters")
    private String username;

    @Past
//    @ApiModelProperty("Birthdate should be in the past")
    private Date birthDate;

    @OneToMany(
            mappedBy = "user"
    )
    private List<Post> posts;
}
