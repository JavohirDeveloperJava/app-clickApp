package uz.pdp.appclickapp.service;

import uz.pdp.appclickapp.entity.User;
import uz.pdp.appclickapp.payload.ApiResponse;
import uz.pdp.appclickapp.payload.ChangeOwnerDto;
import uz.pdp.appclickapp.payload.MemberDTO;
import uz.pdp.appclickapp.payload.WorkspaceDTO;


import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO);

    ApiResponse changeOwnerWorkspace(ChangeOwnerDto dto);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    ApiResponse joinToWorkspace(Long id, User user);

    List<MemberDTO> getMemberGuest(Long id);

    List<WorkspaceDTO> getMyWorkspace(User user);
}