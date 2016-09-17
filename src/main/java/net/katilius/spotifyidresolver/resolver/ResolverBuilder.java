package net.katilius.spotifyidresolver.resolver;

import com.wrapper.spotify.Api;
import net.katilius.spotifyidresolver.resolver.impl.SearchingByNameIdResolver;
import net.katilius.spotifyidresolver.resolver.impl.SequentialIdResolverRunner;
import net.katilius.spotifyidresolver.integration.SpotifyClient;

import java.util.ArrayList;
import java.util.List;

public class ResolverBuilder {
    private SpotifyClient spotifyClient;
    private List<SpotifyTrackIdResolver> resolver;

    private ResolverBuilder(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
        this.resolver = new ArrayList<>();
    }

    public static ResolverBuilder withDefaultSpotifyApi() {
        return new ResolverBuilder(new SpotifyClient(Api.DEFAULT_API));
    }

    public SpotifyTrackIdResolver build() {
        resolver.add(new SearchingByNameIdResolver(spotifyClient));
        return new SequentialIdResolverRunner(resolver);
    }

}
