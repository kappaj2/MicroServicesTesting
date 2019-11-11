package za.co.ajk.in28min.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@ApiModel(description = "All details about the user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Size(min = 2, message = "Name should have at least two characters")
//    @ApiModelProperty("Name should be at least two characters")
    private String username;

    @Past
//    @ApiModelProperty("Birthdate should be in the past")
    private Date birthDate;

    private List<Post> userPosts = new ArrayList<>();
}
