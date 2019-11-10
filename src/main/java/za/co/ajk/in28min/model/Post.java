package za.co.ajk.in28min.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String message;
}
