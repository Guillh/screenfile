package com.guillh.screenfile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class SeasonData {
    @JsonProperty("Season")
    Integer season;
    @JsonProperty("Episodes")
    List<EpisodeData> episodes;
}
