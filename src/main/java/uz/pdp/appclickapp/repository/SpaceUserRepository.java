package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickapp.entity.SpaceUser;

import java.util.List;
import java.util.UUID;

public interface SpaceUserRepository extends JpaRepository<SpaceUser, UUID> {
    List<SpaceUser> findAllBySpaceId(Long space_id);


}