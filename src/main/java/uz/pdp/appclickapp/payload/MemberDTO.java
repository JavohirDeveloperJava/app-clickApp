package uz.pdp.appclickapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uz.pdp.appclickapp.entity.WorkspaceRole;
import uz.pdp.appclickapp.entity.enums.AddType;
import uz.pdp.appclickapp.entity.enums.WorkspaceRoleName;

import java.sql.Timestamp;
import java.util.UUID;
@Data
public class MemberDTO {
    private UUID id;

    private UUID roleId;

    private AddType addType;//ADD, EDIT, REMOVE
}

