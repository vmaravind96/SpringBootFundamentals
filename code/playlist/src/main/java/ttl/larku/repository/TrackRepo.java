package ttl.larku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttl.larku.domain.Track;

@Repository
public interface TrackRepo extends JpaRepository<Track, Integer> {
}
