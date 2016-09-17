package net.katilius.spotifyidresolver.resolver;

import net.katilius.spotifyidresolver.domain.Track;

import java.util.Optional;

public interface SpotifyTrackIdResolver {
    Optional<String> convertToSpotifyId(Track track);
}
