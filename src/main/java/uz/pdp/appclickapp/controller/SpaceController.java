package uz.pdp.appclickapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickapp.entity.User;
import uz.pdp.appclickapp.payload.*;
import uz.pdp.appclickapp.security.CurrentUser;
import uz.pdp.appclickapp.service.SpaceService;

import javax.validation.Valid;

        /*Space va Folder larni CRUD qilish,
        Folderga user qo'shish yoki edit qilish
        va Folder dan user ni o'chirish kabi
        amallarni bajaruvchi method larni yozing.

        */
@RestController
@RequestMapping(value = "/api/space")
public class SpaceController {
    @Autowired
    SpaceService spaceService;

    @GetMapping("/getSpace/{id}")
    public HttpEntity<?> getAllSpace(@PathVariable Long id, @CurrentUser User user){
        ApiResponse apiResponse = spaceService.getAllSpace(id, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/add")
    public HttpEntity<?> addSpace(@Valid @RequestBody SpaceDto dto, @CurrentUser User user){
        ApiResponse apiResponse = spaceService.addSpace(dto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/edet/{id}")
    public HttpEntity<?> edetSpace(@Valid @PathVariable Long id, @RequestBody SpaceDto dto){
        ApiResponse apiResponse = spaceService.edetSpace(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/space/addview/{id}")
    public HttpEntity<?> toSpaceAddView(@Valid @PathVariable Long id, @RequestBody SpaceViewDto dto){
        ApiResponse apiResponse = spaceService.toSpaceAddView(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/space/addclickApp/{id}")
    public HttpEntity<?> toSpaceAddCLickApp(@Valid @RequestBody SpaceClickAppDto dto, @PathVariable Long id){
        ApiResponse apiResponse = spaceService.toSpaceAddCLickApp(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/space/addSpaceUser/{id}")
    public HttpEntity<?> toSpaceAddSpaceUser(@Valid @RequestBody SpaceUserDto dto, @PathVariable Long id){
        ApiResponse apiResponse = spaceService.toSpaceAddSpaceUser(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/space/deletView/{id}")
    public HttpEntity<?> toSpaceDeletView(@Valid @RequestBody SpaceViewDto dto, @PathVariable Long id){
        ApiResponse apiResponse = spaceService.toSpaceDeletView(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/space/deletClickApp/{id}")
    public HttpEntity<?> toSpaceDeletClickApp(@Valid @RequestBody SpaceClickAppDto dto, @PathVariable Long id){
        ApiResponse apiResponse = spaceService.deletClickApp(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/space/deletSpaceUser/{id}")
    public HttpEntity<?> toSpaceDeletSpaceUser(@Valid @RequestBody SpaceUserDto dto, @PathVariable Long id){
        ApiResponse apiResponse = spaceService.toSpaceDeletSpaceUser(dto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteSpace(@PathVariable Long id){
        ApiResponse apiResponse = spaceService.deleteSpace(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
}