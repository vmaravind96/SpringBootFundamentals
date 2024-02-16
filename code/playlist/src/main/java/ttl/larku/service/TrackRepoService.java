package ttl.larku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ttl.larku.domain.Track;
import ttl.larku.repository.TrackRepo;

import java.util.List;

@Service
public class TrackRepoService {

    @Autowired
    private TrackRepo trackRepo;

    public Track createTrack(String title) {
        Track track = Track.title(title).build();
        track = trackRepo.save(track);

        return track;
    }

    public Track createTrack(String title, String artist, String album, String duration, String date) {
        return Track.title(title).artist(artist).album(album).duration(duration).date(date).build();
    }

    public Track createTrack(Track track) {
        track = trackRepo.save(track);

        return track;
    }

    public boolean deleteTrack(int id) {
        Track track = trackRepo.findById(id).orElse(null);
        if (track != null) {
            trackRepo.delete(track);
            return true;
        }
        return false;
    }

    public boolean updateTrack(Track newTrack) {
        Track oldTrack = trackRepo.findById(newTrack.getId()).orElse(null);
        if(oldTrack != null) {
            trackRepo.save(newTrack);
            return true;
        }
        return false;
    }

    public Track getTrack(int id) {
        return trackRepo.findById(id).orElse(null);
    }

    public List<Track> getAllTracks() {
        return trackRepo.findAll();
    }

    public TrackRepo getTrackRepo() {
        return trackRepo;
    }

    public void setTrackRepo(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

    public void clear() {
//        trackRepo.deleteStore();
//        trackRepo.createStore();
    }

}
