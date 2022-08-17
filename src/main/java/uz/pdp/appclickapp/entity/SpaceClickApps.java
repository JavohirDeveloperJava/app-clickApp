package uz.pdp.appclickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickapp.entity.template.AbsLongEntity;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpaceClickApps extends AbsLongEntity {
    @ManyToOne
    private Space space;

    @ManyToOne
    private ClickApps clickApps;
}
