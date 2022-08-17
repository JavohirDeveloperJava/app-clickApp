package uz.pdp.appclickapp.payload;

import lombok.Data;
import uz.pdp.appclickapp.entity.enums.AccessType;


import java.util.List;
import java.util.UUID;

@Data
public class SpaceDto {
    private String name;

    private String color;

    private Long workspaceId;

    private Long iconId;

    private UUID attachmentId;

    private AccessType accessType;

    private List<UUID> member_id;
}
