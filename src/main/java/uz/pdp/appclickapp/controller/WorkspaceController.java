package uz.pdp.appclickapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickapp.entity.User;
import uz.pdp.appclickapp.payload.ApiResponse;
import uz.pdp.appclickapp.payload.ChangeOwnerDto;
import uz.pdp.appclickapp.payload.MemberDTO;
import uz.pdp.appclickapp.payload.WorkspaceDTO;
import uz.pdp.appclickapp.repository.UserRepository;
import uz.pdp.appclickapp.security.CurrentUser;
import uz.pdp.appclickapp.service.WorkspaceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {
    @Autowired
    WorkspaceService workspaceService;

    @PostMapping
    public HttpEntity<?> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * NAME, COLOR, AVATAR O'ZGARAISHI MUMKIN
     *
     * @param id
     * @param workspaceDTO
     * @return
     */
    @PutMapping("/edet")
    public HttpEntity<?> editWorkspace(@RequestParam Long id, @RequestBody WorkspaceDTO workspaceDTO) {
        ApiResponse apiResponse = workspaceService.editWorkspace(id, workspaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * @param
     * @param
     * @return
     */
    @PutMapping("/changeOwner")
    public HttpEntity<?> changeOwnerWorkspace(@RequestBody ChangeOwnerDto dto) {
        ApiResponse apiResponse = workspaceService.changeOwnerWorkspace(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    /**
     * ISHXONANI O'CHIRISH
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long id) {
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/addOrEditOrRemove/{id}")
    public HttpEntity<?> addOrEditOrRemoveWorkspace(@PathVariable Long id,
                                                    @RequestBody MemberDTO memberDTO) {
        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveWorkspace(id, memberDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/join")
    public HttpEntity<?> joinToWorkspace(@RequestParam Long id,
                                         @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.joinToWorkspace(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

     //Member va Guest larni korish

    @GetMapping("/getMemberGuest/{id}")
    public HttpEntity<?> getMemberGuest(@PathVariable Long id){
        List<MemberDTO> members = workspaceService.getMemberGuest(id);
        return ResponseEntity.ok(members);
    }

    //Worspace larni hammasini olish
    @GetMapping()
    public HttpEntity<?> getMyWorkspace(@CurrentUser User user){
        List<WorkspaceDTO> workspaceList = workspaceService.getMyWorkspace(user);
        return ResponseEntity.ok(workspaceList);
    }
}



