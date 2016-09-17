package net.katilius.spotifyidresolver.domain;

public class MusicBrainzItem {
    private final String name;
    private final String id;

    public MusicBrainzItem(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
