package net.katilius.spotifyidresolver.resolver.impl;

import net.katilius.spotifyidresolver.resolver.SpotifyTrackIdResolver;
import net.katilius.spotifyidresolver.resolver.TrackMatcher;
import net.katilius.spotifyidresolver.domain.Track;
import net.katilius.spotifyidresolver.domain.SpotifyTrack;
import net.katilius.spotifyidresolver.integration.SpotifyClient;

import java.util.List;
import java.util.Optional;

/**
 * Resolver that searches Spotify tracks by track, album and artist name and does not uses any ids or
 * other information.
 */
public class SearchingByNameIdResolver implements SpotifyTrackIdResolver {
    private final SpotifyClient spotifyClient;

    public SearchingByNameIdResolver(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    @Override
    public Optional<String> convertToSpotifyId(Track track) {
        List<SpotifyTrack> tracks = spotifyClient.searchTracks(
                track.getName(),
                track.getArtist().getName(),
                track.getAlbum().getName());

        return tracks.stream()
                .filter(spotifyTrack -> TrackMatcher.areTracksMatching(track, spotifyTrack))
                .map(SpotifyTrack::getId)
                .findFirst();
    }

}
