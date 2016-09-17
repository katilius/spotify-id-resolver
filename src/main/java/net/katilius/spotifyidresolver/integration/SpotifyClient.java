package net.katilius.spotifyidresolver.integration;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import net.katilius.spotifyidresolver.domain.SpotifyTrack;
import net.katilius.spotifyidresolver.exception.SpotifyClientException;

import java.util.List;
import java.util.stream.Collectors;

public class SpotifyClient {
    private final Api spotifyApi;

    public SpotifyClient(Api spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public List<SpotifyTrack> searchTracks(String track, String artist, String album) {
        final TrackSearchRequest request = spotifyApi.searchTracks(buildQuery(track, album, artist)).build();
        final Page<Track> trackSearchResult;
        try {
            trackSearchResult = request.get();
        } catch (Exception e) {
            throw new SpotifyClientException(e);
        }
        return trackSearchResult.getItems().stream()
                .map(SpotifyClient::createSpotifyTrack)
                .collect(Collectors.toList());
    }

    private static String buildQuery(String track, String album, String artist) {
        return String.format("track:%s album:%s artist:%s", track, album, artist);
    }

    private static SpotifyTrack createSpotifyTrack(Track track) {
        return new SpotifyTrack(track.getName(), track.getId(),
                track.getArtists().get(0).getName(),
                track.getAlbum().getName());
    }
}
