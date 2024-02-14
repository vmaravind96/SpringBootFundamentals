package ttl.larku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Track;
import ttl.larku.service.TrackService;

import java.util.List;

@SpringBootApplication
public class PlaylistApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistApplication.class, args);
	}

}

@Component
class TrackRunner implements CommandLineRunner {

	@Autowired
	private TrackService trackService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello! from track runner .. !");
		List<Track> tracks = trackService.getAllTracks();
		System.out.println("Tracks size: " + tracks.size());
		tracks.forEach(System.out::println);
	}
}

