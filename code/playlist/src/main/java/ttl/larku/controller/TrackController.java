package ttl.larku.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Track;
import ttl.larku.service.TrackService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks(){
        return ResponseEntity.ok(trackService.getAllTracks());
    }

    @GetMapping("/{tid}")
    public ResponseEntity<?> getTrack(@PathVariable(name="tid") int tid){
        Track track = trackService.getTrack(tid);
        if (track == null) {
            return ResponseEntity.badRequest().body("No track with id: " + tid);
        }
        return ResponseEntity.ok(track);
    }

    @PostMapping
    public ResponseEntity<?> createTrack(@RequestBody Track track){
        Track newTrack = trackService.createTrack(track);
        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTrack.getId())
                .toUri();
        return ResponseEntity.created(newResource).build();
    }

    @DeleteMapping("/{tid}")
    public ResponseEntity<?> deleteTrack(@PathVariable(name="tid") int tid){
        boolean result = trackService.deleteTrack(tid);
        if (!result) {
            return ResponseEntity.badRequest().body("No track with id: " + tid);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{tid}")
    public ResponseEntity<?> updateTrack(@PathVariable(name="tid") int tid, @RequestBody Track toUpdate){
        boolean result = trackService.updateTrack(toUpdate);
        if (!result) {
            return ResponseEntity.badRequest().body("No track with id: " + tid);
        }
        return ResponseEntity.noContent().build();
    }

}
