package uz.pdp.appclickapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;
@Data
public class ChangeOwnerDto {
    @NotNull
    private Long id;

    @NotNull
    private UUID ownerId;
}
