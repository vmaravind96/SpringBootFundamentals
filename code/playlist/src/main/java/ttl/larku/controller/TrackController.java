package ttl.larku.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ttl.larku.domain.Track;
import ttl.larku.service.TrackService;

import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public List<Track> getAllTracks(){
        return trackService.getAllTracks();
    }

    @GetMapping("/{tid}")
    public Track getTrack(@PathVariable(name="tid") int tid){
        return trackService.getTrack(tid);
    }

    @PostMapping("/create")
    public Track createTrack(@RequestBody Track track){
        return trackService.createTrack(track);
    }
}
