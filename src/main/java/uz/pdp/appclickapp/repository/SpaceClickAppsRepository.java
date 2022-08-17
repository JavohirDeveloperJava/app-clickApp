package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickapp.entity.SpaceClickApps;

@Repository
public interface SpaceClickAppsRepository extends JpaRepository<SpaceClickApps, Long> {
    void deleteBySpaceIdAndId(Long space_id, Long id);
}