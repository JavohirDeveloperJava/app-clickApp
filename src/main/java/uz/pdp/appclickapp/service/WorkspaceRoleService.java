package uz.pdp.appclickapp.service;

import uz.pdp.appclickapp.payload.ApiResponse;
import uz.pdp.appclickapp.payload.WorkspaceRoleDto;

public interface WorkspaceRoleService {

    ApiResponse addWorkspaceRole(WorkspaceRoleDto dto);
}
