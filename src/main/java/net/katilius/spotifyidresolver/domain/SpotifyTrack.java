package net.katilius.spotifyidresolver.domain;

public class SpotifyTrack {
    private final String name;
    private final String id;
    private final String artistName;
    private final String albumName;

    public SpotifyTrack(String name, String id, String artistName, String albumName) {
        this.name = name;
        this.id = id;
        this.artistName = artistName;
        this.albumName = albumName;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }
}
