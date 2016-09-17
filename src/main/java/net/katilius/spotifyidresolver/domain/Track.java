package net.katilius.spotifyidresolver.domain;

import java.util.Objects;

public class Track extends MusicBrainzItem {
    private final MusicBrainzItem artist;
    private final MusicBrainzItem album;

    public Track(String name, String id, MusicBrainzItem artist, MusicBrainzItem album) {
        super(name, id);
        this.artist = Objects.requireNonNull(artist);
        this.album = Objects.requireNonNull(album);
    }

    public MusicBrainzItem getAlbum() {
        return album;
    }

    public MusicBrainzItem getArtist() {
        return artist;
    }

    public static TrackBuilder builder() {
        return new TrackBuilder();
    }
}
