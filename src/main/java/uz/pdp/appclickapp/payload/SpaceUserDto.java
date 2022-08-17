package uz.pdp.appclickapp.payload;

import lombok.Data;
import uz.pdp.appclickapp.entity.enums.AccessType;

import java.util.List;
import java.util.UUID;

@Data
public class SpaceUserDto {

    private List<UUID> userId;


    private AccessType accessType;


}