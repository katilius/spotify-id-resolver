package net.katilius.spotifyidresolver.resolver.impl;

import net.katilius.spotifyidresolver.resolver.SpotifyTrackIdResolver;
import net.katilius.spotifyidresolver.domain.Track;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SequentialIdResolverRunner implements SpotifyTrackIdResolver {
    private final List<SpotifyTrackIdResolver> resolvers;

    public SequentialIdResolverRunner(List<SpotifyTrackIdResolver> resolvers) {
        this.resolvers = Objects.requireNonNull(resolvers);
    }

    @Override
    public Optional<String> convertToSpotifyId(Track track) {
        for (SpotifyTrackIdResolver resolver : resolvers) {
            Optional<String> result = resolver.convertToSpotifyId(track);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }
}
