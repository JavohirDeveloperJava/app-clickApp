package uz.pdp.appclickapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.appclickapp.entity.*;
import uz.pdp.appclickapp.entity.enums.AccessType;
import uz.pdp.appclickapp.payload.*;
import uz.pdp.appclickapp.repository.*;
import uz.pdp.appclickapp.service.SpaceService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpaceServiceImpl implements SpaceService {
    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    IconRepository iconRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    WorkspaceUserRepository workspaceUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpaceUserRepository spaceUserRepository;

    @Autowired
    SpaceClickAppsRepository spaceClickAppsRepository;

    @Autowired
    SpaceViewRepository spaceViewRepository;

    @Autowired
    ViewRepository viewRepository;

    @Autowired
    ClickAppsRepository clickAppsRepository;
    /*
    User va workspacega tegishli SpaceDto
     */

    @Override
    public ApiResponse getAllSpace(Long id, User users) {
        List<Space> spaceList = spaceRepository.getAllByWorkspaceIsAndUserId(id, users.getId());
        return new ApiResponse("Success", true, spaceList);
    }

    @Override
    public ApiResponse addSpace(SpaceDto dto, User users) {
        if (spaceRepository.existsByNameAndWorkspaceId(dto.getName(), dto.getWorkspaceId()))
            return new ApiResponse("Bunday space mavjud boshqa kiriting", false);
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(dto.getWorkspaceId());
        if (!optionalWorkspace.isPresent())
            return new ApiResponse("Bunday workspace toplimadi", false);
        Workspace workspace = optionalWorkspace.get();
        Space space = new Space(
                dto.getName(),
                dto.getColor(),
                workspace,
                dto.getIconId()==null?null:iconRepository.findById(dto.getIconId()).orElseThrow(() -> new ResourceNotFoundException("Icon")),
                dto.getAttachmentId()==null?null:attachmentRepository.findById(dto.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("attachmetn")),
                users,
                dto.getAccessType()
        );
        spaceRepository.save(space);
        List<SpaceUser> userList = new ArrayList<>();
        if (dto.getAccessType().equals(AccessType.PUBLIC)){
            List<WorkspaceUser> workspaceUserList = workspaceUserRepository.findAllByWorkspaceId(dto.getWorkspaceId());
            for (WorkspaceUser workspaceUser : workspaceUserList) {
                userList.add(new SpaceUser(space, workspaceUser.getUser()));
            }
        }else {
            for (UUID uuid : dto.getMember_id()) {
                if (workspaceUserRepository.findByWorkspaceIdAndUserId(workspace.getId(), uuid).isPresent()){
                    userList.add(new SpaceUser(space, userRepository.getById(uuid)));
                }
            }
        }
        spaceUserRepository.saveAll(userList);
        spaceViewRepository.save(new SpaceView(space, viewRepository.getByName("List")));
        spaceClickAppsRepository.save(new SpaceClickApps(space, clickAppsRepository.getByName("Priority")));
        return new ApiResponse("Saqlandi", true);
    }

    /*
    Space EDET qilish User larni biriktirish Space ni PUBLIC VA PRIVATE QILISH
     */
    @Override
    public ApiResponse edetSpace(Long id, SpaceDto dto) {
        if (!spaceRepository.existsByNameAndWorkspaceIdAndId(dto.getName(), dto.getWorkspaceId(), id))
            return new ApiResponse("Bunday space mavjud emas", false);
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (!optionalSpace.isPresent())
            return new ApiResponse("Space topilmadi", false);
        Space space = optionalSpace.get();
        space.setName(dto.getName());
        space.setColor(dto.getColor());
        space.setIcon(iconRepository.findById(dto.getIconId()).orElseThrow(() -> new ResourceNotFoundException("Icon")));
        space.setAttachment(attachmentRepository.findById(dto.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("Attachment")));
        if (!space.getAccessType().equals(dto.getAccessType())){
            space.setAccessType(dto.getAccessType());
            edetMembers(space, dto.getMember_id(),"Save");
        }
        spaceRepository.save(space);
        return new ApiResponse("Updated", true);

    }

    /*
    Delete space
     */
    @Override
    public ApiResponse deleteSpace(Long id) {
        if (!spaceRepository.existsById(id))
            return new ApiResponse("Space not found ", false);

        spaceRepository.deleteById(id);
            return new ApiResponse("Delete space success", true);
    }
    /*
      Space ga view biriktirish
     */
    @Override
    public ApiResponse toSpaceAddView(Long id, SpaceViewDto dto) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (!optionalSpace.isPresent())
            return new ApiResponse("Space topilmadi",false);
        Space space = optionalSpace.get();
        List<SpaceView> viewSpaceList = new ArrayList<>();
        for (Long views : dto.getViewId()) {
            View view = viewRepository.findById(views).orElseThrow(() -> new ResourceNotFoundException("view"));
            SpaceView spaceView = new SpaceView(space, view);
            viewSpaceList.add(spaceView);
        }
        spaceViewRepository.saveAll(viewSpaceList);
        return new ApiResponse("Save all view", true);
    }
    /*
    Space ga ClickApp biriktirish
     */

    @Override
    public ApiResponse toSpaceAddCLickApp(Long id, SpaceClickAppDto dto) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (!optionalSpace.isPresent())
            return new ApiResponse("Space topilmadi",false);
        Space space = optionalSpace.get();
        List<SpaceClickApps> spaceClickAppsList = new ArrayList<>();

        for (Long clickApp : dto.getClickApps()) {
            ClickApps clickApps = clickAppsRepository.findById(clickApp).orElseThrow(() -> new ResourceNotFoundException("ClickApps"));
            SpaceClickApps spaceClickApps = new SpaceClickApps(space, clickApps);
            spaceClickAppsList.add(spaceClickApps);
        }
        spaceClickAppsRepository.saveAll(spaceClickAppsList);
        return new ApiResponse("Save all success", true);
    }
    /*
    Space ga WorkspaceUserlarni qoshish
     */

    @Override
    public ApiResponse toSpaceAddSpaceUser(SpaceUserDto dto, Long id) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (!optionalSpace.isPresent())
            return new ApiResponse("Space topilmadi",false);
        Space space = optionalSpace.get();
        if (!space.getAccessType().equals(dto.getAccessType())){
            space.setAccessType(dto.getAccessType());
            edetMembers(space, dto.getUserId(), "Save");
        }
        spaceRepository.save(space);
        return new ApiResponse("Save success", true);
    }
    /*
    Delete Space da View
     */

    @Override
    public ApiResponse toSpaceDeletView(SpaceViewDto dto, Long id) {
        if (!spaceRepository.existsById(id))
            return new ApiResponse("Space not found", false);
        for (Long view : dto.getViewId()) {
            spaceViewRepository.deleteBySpaceIdAndId(id, view);
        }
        return new ApiResponse("No active view", true);
    }
    /*
    Delete Space dan ClickApps
     */

    @Override
    public ApiResponse deletClickApp(SpaceClickAppDto dto, Long id) {
        if (!spaceRepository.existsById(id))
            return new ApiResponse("Space not found", false);
        for (Long app : dto.getClickApps()) {
            spaceClickAppsRepository.deleteBySpaceIdAndId(id, app);
        }
        return new ApiResponse("Delete ClickApp", true);
    }
    /*
    Delete Space dan SpaceUser
     */

    @Override
    public ApiResponse toSpaceDeletSpaceUser(SpaceUserDto dto, Long id) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (!optionalSpace.isPresent())
            return new ApiResponse("Space not found", false);
        Space space = optionalSpace.get();
        if (space.getAccessType().equals(dto.getAccessType())){
            edetMembers(space, dto.getUserId(), "Delete");
        }
        return new ApiResponse("Delete SpaceUser", true);
    }

    private void edetMembers(Space space, List<UUID> members, String operation){
        List<SpaceUser> spaceUserList = spaceUserRepository.findAllBySpaceId(space.getId());
        if (operation.equals("Save")){
            List<SpaceUser> spaceUsers = new ArrayList<>(spaceUserList);
            for (UUID member : members) {
                if(workspaceUserRepository.findByWorkspaceIdAndUserId(space.getWorkspace().getId(), member).isPresent()){
                    boolean has = false;
                    for (SpaceUser spaceUser : spaceUserList) {
                        if (spaceUser.getMember().getId().equals(member)){
                            has = true;
                            break;
                        }
                    }
                    if (!has){
                        spaceUsers.add(new SpaceUser(space,userRepository.getById(member)));
                    }
                }

            }
            spaceUserRepository.saveAll(spaceUsers);
        }else if (operation.equals("Delete")){
            List<SpaceUser> deleteUser = new ArrayList<>();
            for (UUID member : members) {
                for (SpaceUser spaceUser : spaceUserList) {
                    if (workspaceUserRepository.findByWorkspaceIdAndUserId(space.getWorkspace().getId(), member).isPresent()){
                        if (spaceUser.getMember().getId().equals(member)){
                            deleteUser.add(spaceUser);
                        }
                    }
                }
            }
            spaceUserRepository.deleteAll(deleteUser);
        }
    }
}
