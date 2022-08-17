package uz.pdp.appclickapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspaceRoleDto {
    @NotNull
    private Long workspaceId;

    @NotBlank
    private String name;

    @NotNull
    private UUID extendsRole;
}
