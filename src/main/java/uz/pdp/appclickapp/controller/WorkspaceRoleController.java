package uz.pdp.appclickapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appclickapp.payload.ApiResponse;
import uz.pdp.appclickapp.payload.WorkspaceRoleDto;
import uz.pdp.appclickapp.service.WorkspaceRoleService;


@RestController
@RequestMapping(value = "/api/role")
public class WorkspaceRoleController {

    @Autowired
    WorkspaceRoleService workspaceRoleService;



    /*
    Workspace add role
     */
    @PostMapping("/addRole")
    public HttpEntity<?> addWorkspaceRole(@RequestBody WorkspaceRoleDto dto){
        ApiResponse apiResponse = workspaceRoleService.addWorkspaceRole(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }





}
