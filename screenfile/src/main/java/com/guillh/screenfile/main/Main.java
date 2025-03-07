package com.guillh.screenfile.main;

import com.guillh.screenfile.model.EpisodeData;
import com.guillh.screenfile.model.SeasonData;
import com.guillh.screenfile.model.SerieData;
import com.guillh.screenfile.service.ExternalApi;
import com.guillh.screenfile.service.ProcessData;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner read = new Scanner(System.in);
    private final String DOMAIN = "https://www.omdbapi.com";
    private final String TITLE_PATH = "/?t=";
    private final String SEASON_PATH = "&Season=";
    private final String EPISODE_PATH = "&Episode=";
    private final String API_KEY = "&apikey=df118d9d";
    private ExternalApi externalApi = new ExternalApi();
    private ProcessData processData = new ProcessData();
    public void showMenu() {
        String serieName = "";
        String url;
        String json;
        System.out.println("Type serie's name to search: ");
        serieName = read.nextLine().replaceAll(" ", "+");
        url = DOMAIN.concat(TITLE_PATH).concat(serieName).concat(API_KEY);
        json = externalApi.getData(url);
        SerieData serieData = processData.getData(json, SerieData.class);
        System.out.println(serieData);

		int totalSeasons = serieData.getSeasons();
		List<SeasonData> seasonDataList = new ArrayList<>();
		for(Integer seasons = 1; seasons <= totalSeasons; seasons++) {
            url = DOMAIN.concat(TITLE_PATH).concat(serieName).concat(SEASON_PATH).concat(seasons.toString()).concat(API_KEY);
			json = externalApi.getData(String.format(url, seasons));
            SeasonData seasonData = processData.getData(json, SeasonData.class);
			seasonDataList.add(seasonData);
		}

        List<EpisodeData> episodeDataList = seasonDataList.stream()
                                        .flatMap(seasonData -> seasonData.getEpisodes()
                                                                                    .stream()
                                                                                    .map(episodeData -> new EpisodeData(seasonData.getSeason(), episodeData))
                                        )
                                        .toList();

        episodeDataList.forEach(System.out::println);

        System.out.println("\nType a episode name that you want to find: ");
        String titleSlice = read.nextLine();
        Optional<EpisodeData> collectedEpisode = episodeDataList.stream()
                       .filter(episodeData -> episodeData.getTitle().toUpperCase().contains(titleSlice.toUpperCase()))
                       .findFirst();
        if(collectedEpisode.isPresent()) {
            System.out.println(collectedEpisode.get());
        } else {
            System.out.println("Episode not find!");
        }

//        System.out.println("\nTop 10 episodes by IMDB:");
//        episodeDataList.stream()
//                .filter(episodeData -> !episodeData.getImdbRating().equals("N/A"))
//                .sorted(Comparator.comparing(EpisodeData::getImdbRating).reversed())
//                .limit(10)
//                .map(episodeData -> episodeData.getTitle().toUpperCase())
//                .forEach(System.out::println);
//                .forEach( episode -> System.out.println(" - Episode " + episode.getEpisode() + " - Title: " + episode.getTitle() + " - Season: " +  episode.getSeason()));

//        System.out.println("\nFrom what year do you want to see?");
//        int yearFilter = read.nextInt();
//        read.nextLine();
//
//        LocalDate dateSearch = LocalDate.of(yearFilter,1,1);
//
//        episodeDataList.stream()
//                       .filter(episodeData -> episodeData.getReleaseDate() != null && episodeData.getReleaseDate().isAfter(dateSearch))
//                       .forEach(episodeData -> System.out.println(
//                               "Season: " + episodeData.getSeason() +
//                               " - Episode: " + episodeData.getTitle() +
//                               " - Release date: " + episodeData.getReleaseDate()
//                       ));
    }
}

