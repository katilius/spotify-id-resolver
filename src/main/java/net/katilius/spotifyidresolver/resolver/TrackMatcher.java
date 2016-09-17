package net.katilius.spotifyidresolver.resolver;

import net.katilius.spotifyidresolver.domain.Track;
import net.katilius.spotifyidresolver.domain.SpotifyTrack;

public class TrackMatcher {
    public static boolean areTracksMatching(Track track, SpotifyTrack spotifyTrack) {
        boolean trackNameMatches = track.getName().equalsIgnoreCase(spotifyTrack.getName());
        boolean artistMatches = track.getArtist().getName().equalsIgnoreCase(spotifyTrack.getArtistName());
        boolean albumMatches = track.getAlbum().getName().equalsIgnoreCase(spotifyTrack.getAlbumName());
        return trackNameMatches && artistMatches && albumMatches;
    }
}
