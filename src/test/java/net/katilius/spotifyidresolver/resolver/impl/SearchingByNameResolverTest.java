package net.katilius.spotifyidresolver.resolver.impl;

import net.katilius.spotifyidresolver.domain.Track;
import net.katilius.spotifyidresolver.domain.TrackBuilder;
import net.katilius.spotifyidresolver.domain.SpotifyTrack;
import net.katilius.spotifyidresolver.integration.SpotifyClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SearchingByNameResolverTest {

    @Mock
    SpotifyClient spotifyClient;

    private SearchingByNameIdResolver resolver;

    @Before
    public void init() {
        resolver = new SearchingByNameIdResolver(spotifyClient);
    }

    @Test
    public void convertToSpotifyId_ResultContainMatchingSong() {
        Track track = new TrackBuilder()
                .withTrackName("Elegia - 2015 Remastered Version")
                .withTrackMusicBrainzId("f1106b17-dcbb-45f6-b938-199ccfab50cc")
                .withArtistName("New Order")
                .withAlbumName("Low-Life")
                .build();

        List<SpotifyTrack> returnedTracks = Arrays.asList(
                new SpotifyTrack("Elegia", "id1", "New Order", "Low-Life"),
                new SpotifyTrack("Elegia - 2015 Remastered Version", "id2", "New Order", "Low-Life"));
        Mockito.when(spotifyClient.searchTracks(track.getName(), track.getArtist().getName(), track.getAlbum().getName()))
                .thenReturn(returnedTracks);

        assertThat(resolver.convertToSpotifyId(track)).contains("id2");
    }


    @Test
    public void convertToSpotifyId_NoResults() {
        Track track = Track.builder()
                .withTrackName("Initiate")
                .withTrackMusicBrainzId("763Cdoyt04BBp8FgGuUrXO")
                .withArtistName("Haken")
                .withAlbumName("Affinity")
                .build();

        Mockito.when(spotifyClient.searchTracks(track.getName(), track.getArtist().getName(), track.getAlbum().getName()))
                .thenReturn(Collections.emptyList());

        assertThat(resolver.convertToSpotifyId(track)).isEmpty();
    }

}