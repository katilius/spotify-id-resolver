package net.katilius.spotifyidresolver.domain;


import net.katilius.spotifyidresolver.exception.NoTrackInformationException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TrackBuilderTest {
    @Test
    public void build_AllFields() {
        Track track = Track.builder()
                .withTrackName("A Great Day For Freedom - 2011 Remastered Version")
                .withTrackMusicBrainzId("TRACK_MB_ID1")
                .withArtistName("Pink Floyd")
                .withArtistMusicBrainzId("ARTIST_MB_ID1")
                .withAlbumName("The Division Bell (2011 Remastered Version)")
                .withAlbumMusicBrainzId("ALBUM_MB_ID2")
                .build();
        assertThat(track).isNotNull();
        assertThat(track.getName()).isEqualTo("A Great Day For Freedom - 2011 Remastered Version");
        assertThat(track.getId()).isEqualTo("TRACK_MB_ID1");
        MusicBrainzItem album = track.getAlbum();
        assertThat(album).isNotNull();
        assertThat(album.getName()).isEqualTo("The Division Bell (2011 Remastered Version)");
        assertThat(album.getId()).isEqualTo("ALBUM_MB_ID2");
        MusicBrainzItem artist = track.getArtist();
        assertThat(artist).isNotNull();
        assertThat(artist.getName()).isEqualTo("Pink Floyd");
        assertThat(artist.getId()).isEqualTo("ARTIST_MB_ID1");
    }

    @Test
    public void build_OnlyTrackInfo() {
        Track track = Track.builder()
                .withTrackName("Oiseaux de proie")
                .withTrackMusicBrainzId("TRACK_MB_ID3")
                .build();
        assertThat(track).isNotNull();
        assertThat(track.getName()).isEqualTo("Oiseaux de proie");
        assertThat(track.getId()).isEqualTo("TRACK_MB_ID3");
    }

    @Test
    public void build_OnlyTrackAndArtist() {
        Track track = Track.builder()
                .withTrackName("Oiseaux de proie")
                .withTrackMusicBrainzId("TRACK_MB_ID4")
                .withArtistName("Alcest")
                .withArtistMusicBrainzId("ARTIST_MB_ID2")
                .build();
        assertThat(track).isNotNull();
        assertThat(track.getName()).isEqualTo("Oiseaux de proie");
        assertThat(track.getId()).isEqualTo("TRACK_MB_ID4");
    }

    @Test
    public void build_NoTrackInformationAtAll() {
        assertThatThrownBy(() -> Track.builder().build())
                .isInstanceOf(NoTrackInformationException.class);
    }

}