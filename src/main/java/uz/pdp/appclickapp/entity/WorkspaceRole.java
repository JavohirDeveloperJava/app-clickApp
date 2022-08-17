package uz.pdp.appclickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickapp.entity.enums.WorkspaceRoleName;
import uz.pdp.appclickapp.entity.template.AbsLongEntity;
import uz.pdp.appclickapp.entity.template.AbsUUIDEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceRole extends AbsUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;//ishxonaga tegishlik

    @Column(nullable = false)
    private String name;//lavozim name OWNER(EGASI),ADMIN,GUEST(MEHMON)

    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendsRole;
}