package uz.pdp.appclickapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appclickapp.entity.Workspace;
import uz.pdp.appclickapp.entity.WorkspacePermission;
import uz.pdp.appclickapp.entity.WorkspaceRole;
import uz.pdp.appclickapp.entity.enums.WorkspaceRoleName;
import uz.pdp.appclickapp.payload.ApiResponse;
import uz.pdp.appclickapp.payload.WorkspaceRoleDto;
import uz.pdp.appclickapp.repository.WorkspacePermissionRepository;
import uz.pdp.appclickapp.repository.WorkspaceRepository;
import uz.pdp.appclickapp.repository.WorkspaceRoleRepository;
import uz.pdp.appclickapp.service.WorkspaceRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceRoleServiceImpl implements WorkspaceRoleService {
    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;

    @Override
    public ApiResponse addWorkspaceRole(WorkspaceRoleDto dto) {

        if (workspaceRoleRepository.existsByWorkspaceIdAndName(dto.getWorkspaceId(), dto.getName()))
            return new ApiResponse("Bu workspace da bunday role mavjud!", false);
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(dto.getWorkspaceId());
        if (!optionalWorkspace.isPresent())
            return new ApiResponse("Bunday workspace mavjud emas", false);
        Workspace workspace = optionalWorkspace.get();
        Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(dto.getExtendsRole());
        if (!optionalWorkspaceRole.isPresent())
            return new ApiResponse("Bunday role mavjud emas", false);
        WorkspaceRole extendsRole = optionalWorkspaceRole.get();

        WorkspaceRole workspaceRole = new WorkspaceRole(
                workspace,
                dto.getName(),
                WorkspaceRoleName.valueOf(extendsRole.getName())
        );
        workspaceRoleRepository.save(workspaceRole);

        List<WorkspacePermission> permissionList = new ArrayList<>();
        List<WorkspacePermission> permissions =
                workspacePermissionRepository.findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(extendsRole.getName(), dto.getWorkspaceId());
        for (WorkspacePermission permission : permissions) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    workspaceRole,
                    permission.getPermission()
            );
            permissionList.add(workspacePermission);
        }
        workspacePermissionRepository.saveAll(permissionList);
        return new ApiResponse("Saqlandi", true);
    }
}
