package com.guillh.screenfile.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class SerieData {
    @JsonAlias("Title")
    String title;
    @JsonAlias("Released")
    String realeaseDate;
    @JsonAlias("Genre")
    String genre;
    @JsonAlias("Awards")
    String awards;
    @JsonAlias("imdbRating")
    String imdbRating;
    @JsonAlias("totalSeasons")
    Integer seasons;
    @JsonAlias("Plot")
    String plot;
}

