package com.App.fawryTask.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "movie")
@Setter
@Getter
public class Movies {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty
     private Long Id;
     @JsonProperty
     private String title;
     @JsonProperty
    private  String  year;
     @JsonProperty
    private String description;
     @JsonProperty
     private int imdbId;

}
