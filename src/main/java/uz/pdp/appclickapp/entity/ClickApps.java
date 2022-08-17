package uz.pdp.appclickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickapp.entity.template.AbsLongEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClickApps extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Icon icon;
}
