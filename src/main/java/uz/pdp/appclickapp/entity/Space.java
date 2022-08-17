package uz.pdp.appclickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickapp.entity.template.AbsLongEntity;

import uz.pdp.appclickapp.entity.enums.AccessType;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "workspace_id"})})
public class Space extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;

    @Column(nullable = false)
    private String initialLetter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Icon icon;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;

    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod() {
        this.initialLetter = name.substring(0, 1);
    }

    public Space(String name, String color, Workspace workspace, Icon icon, Attachment attachment, User owner, AccessType accessType) {
        this.name = name;
        this.color = color;
        this.workspace = workspace;
        this.icon = icon;
        this.attachment = attachment;
        this.owner = owner;
        this.accessType = accessType;
    }
}
