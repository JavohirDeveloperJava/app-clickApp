package uz.pdp.appclickapp.service;


import uz.pdp.appclickapp.entity.User;
import uz.pdp.appclickapp.payload.*;


public interface SpaceService {

    ApiResponse addSpace(SpaceDto dto, User user);

    ApiResponse edetSpace(Long id, SpaceDto dto);

    ApiResponse deleteSpace(Long id);

    ApiResponse toSpaceAddView(Long id, SpaceViewDto dto);

    ApiResponse toSpaceAddCLickApp(Long id, SpaceClickAppDto dto);

    ApiResponse toSpaceAddSpaceUser(SpaceUserDto dto, Long id);

    ApiResponse toSpaceDeletView(SpaceViewDto dto, Long id);

    ApiResponse deletClickApp(SpaceClickAppDto dto, Long id);

    ApiResponse toSpaceDeletSpaceUser(SpaceUserDto dto, Long id);

    ApiResponse getAllSpace(Long id, User user);
}
