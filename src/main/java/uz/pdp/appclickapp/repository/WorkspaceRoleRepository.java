package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickapp.entity.WorkspaceRole;

import java.util.UUID;


public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {

    boolean existsByWorkspaceIdAndName(Long workspace_id, String name);
}
