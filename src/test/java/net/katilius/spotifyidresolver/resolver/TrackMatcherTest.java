package net.katilius.spotifyidresolver.resolver;

import net.katilius.spotifyidresolver.domain.Track;
import net.katilius.spotifyidresolver.domain.SpotifyTrack;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackMatcherTest {

    @Test
    public void areTracksMatching_SameTracks() {
        SpotifyTrack spotifyTrack = buildSpotifyTrack("Into The Back", "Samsara Blues Experiment", "Revelation & Mystery");
        Track track = buildTrack("Into The Back", "Samsara Blues Experiment", "Revelation & Mystery");
        assertThat(TrackMatcher.areTracksMatching(track, spotifyTrack)).isTrue();
    }

    @Test
    public void areTracksMatching_DifferentTracks() {
        SpotifyTrack spotifyTrack = buildSpotifyTrack("Into The Back [Live]", "Samsara Blues Experiment", "Revelation & Mystery");
        Track track = buildTrack("Into The Back", "Samsara Blues Experiment", "Revelation & Mystery");
        assertThat(TrackMatcher.areTracksMatching(track, spotifyTrack)).isFalse();
    }

    @Test
    public void areTracksMatching_SameTracksButDifferentCases() {
        SpotifyTrack spotifyTrack = buildSpotifyTrack("into the back", "samsara blues experiment", "revelation & Mystery");
        Track track = buildTrack("Into The Back", "Samsara Blues Experiment", "Revelation & Mystery");
        assertThat(TrackMatcher.areTracksMatching(track, spotifyTrack)).isTrue();
    }


    @Test
    public void areTracksMatching_DifferentAlbum() {
        SpotifyTrack spotifyTrack = buildSpotifyTrack("The Melancholy Spirit", "Agalloch", "Pale Folklore");
        Track track = buildTrack("The Melancholy Spirit", "Agalloch", "Pale Folklore (Remastered)");
        assertThat(TrackMatcher.areTracksMatching(track, spotifyTrack)).isFalse();
    }

    private static Track buildTrack(String name, String albumName, String artistName) {
        return Track.builder()
                .withTrackName(name)
                .withArtistName(artistName)
                .withAlbumName(albumName)
                .build();
    }

    private static SpotifyTrack buildSpotifyTrack(String name, String albumName, String artistName) {
        return new SpotifyTrack(name, "123", artistName, albumName);
    }
}