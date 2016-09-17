package net.katilius.spotifyidresolver.resolver;

import net.katilius.spotifyidresolver.domain.Track;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResolverIT {

    @Test
    public void withDefaultSpotifyApi() throws Exception {
        SpotifyTrackIdResolver resolver = ResolverBuilder.withDefaultSpotifyApi().build();
        Track track = Track.builder()
                .withTrackName("Hunt the Buffalo")
                .withArtistName("Cobalt")
                .withAlbumName("Slow Forever")
                .build();
        assertThat(resolver.convertToSpotifyId(track)).hasValue("1HRwKWGgkRdWPqpVW68FoA");
    }
}