package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickapp.entity.WorkspacePermission;

import java.util.List;
import java.util.UUID;
public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {

    List<WorkspacePermission> findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(String workspaceRole_name, Long workspaceRole_workspace_id);

}
