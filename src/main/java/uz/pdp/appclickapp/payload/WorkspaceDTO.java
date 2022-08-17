package uz.pdp.appclickapp.payload;

import lombok.Data;
import uz.pdp.appclickapp.entity.Attachment;
import uz.pdp.appclickapp.entity.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspaceDTO {
    @NotNull
    private String name;

    @NotNull
    private String color;

    private UUID avatarId;

}
