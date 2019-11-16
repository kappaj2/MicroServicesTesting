package za.co.ajk.in28min.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
   // @JoinColumn(name = "user_id")
    private User user;

//    @Override
//    public String toString() {
//        return "Post{" +
//                "id=" + id +
//                ", message='" + message + '\'' +
//                '}';
//    }
}
