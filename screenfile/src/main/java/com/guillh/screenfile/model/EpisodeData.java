package com.guillh.screenfile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeData {
    @JsonProperty("Title")
    String title;
    @JsonProperty("Episode")
    Integer episode;
    @JsonProperty("imdbRating")
    Double imdbRating;
    LocalDate releaseDate;
    Integer season;

    @JsonSetter("imdbRating")
    public void setImdbRating(String imdbRating) {
        try {
            this.imdbRating = imdbRating.equals("N/A") ? 0.0 : Double.parseDouble(imdbRating);
        } catch (NumberFormatException ex) {
            this.imdbRating = 0.0;
        }
    }

    @JsonProperty("Released")
    @JsonSetter("Released")
    public void setReleaseDate(String released) {
        if (released == null || released.equalsIgnoreCase("N/A")) {
            this.releaseDate = null;
        } else {
            try {
                this.releaseDate = LocalDate.parse(released, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                this.releaseDate = null;
            }
        }
    }

    public EpisodeData(Integer seasonNumber, EpisodeData episodeData) {
        this.season = seasonNumber;
        this.episode = episodeData.getEpisode();
        try {
            this.imdbRating = episodeData.getImdbRating();
        } catch (NumberFormatException ex) {
            this.imdbRating =  0.0;
        }
        try {
            this.releaseDate = episodeData.getReleaseDate();
        } catch (DateTimeParseException ex) {
            this.releaseDate = null;
        }
        this.title = episodeData.getTitle();
    }

    @Override
    public String toString() {
        return "title='" + this.title +
                ", episode=" + this.episode +
                ", imdbRating=" + this.imdbRating +
                ", releaseDate=" + this.releaseDate +
                ", season=" + this.season;
    }
}
