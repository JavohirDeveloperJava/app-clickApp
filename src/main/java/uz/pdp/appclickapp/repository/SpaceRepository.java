package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appclickapp.entity.Space;

import java.util.List;
import java.util.UUID;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    boolean existsByNameAndWorkspaceId(String name, Long workspace_id);

    boolean existsByNameAndWorkspaceIdAndId(String name, Long workspace_id, Long id);

    boolean existsById(Long id);

    @Query(value = "SELECT * from space sp\n" +
            "            join space_user spu on sp.id = spu.space_id\n" +
            "            where spu.member_id =:userId and sp.workspace_id =:workspaceId", nativeQuery = true)
    List<Space> getAllByWorkspaceIsAndUserId(Long workspaceId, UUID userId);
}