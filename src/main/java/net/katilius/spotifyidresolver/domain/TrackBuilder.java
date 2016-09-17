package net.katilius.spotifyidresolver.domain;

import net.katilius.spotifyidresolver.exception.NoTrackInformationException;

public class TrackBuilder {
    private String trackName;
    private String trackMusicBrainzId;
    private String artistName;
    private String artistMusicBrainzId;
    private String albumName;
    private String albumMusicBrainzId;

    public TrackBuilder withTrackName(String name) {
        this.trackName = name;
        return this;
    }

    public TrackBuilder withTrackMusicBrainzId(String id) {
        this.trackMusicBrainzId = id;
        return this;
    }

    public TrackBuilder withAlbumMusicBrainzId(String id) {
        this.albumMusicBrainzId = id;
        return this;
    }

    public TrackBuilder withArtistMusicBrainzId(String id) {
        this.artistMusicBrainzId = id;
        return this;
    }

    public TrackBuilder withArtistName(String artistName) {
        this.artistName = artistName;
        return this;
    }

    public TrackBuilder withAlbumName(String albumName) {
        this.albumName = albumName;
        return this;
    }

    public Track build() {
        if (trackName == null && trackMusicBrainzId == null) {
            throw new NoTrackInformationException();
        }
        MusicBrainzItem artist = new MusicBrainzItem(artistName, artistMusicBrainzId);
        MusicBrainzItem album = new MusicBrainzItem(albumName, albumMusicBrainzId);

        return new Track(trackName, trackMusicBrainzId, artist, album);
    }

}