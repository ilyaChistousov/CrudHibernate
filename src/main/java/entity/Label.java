package entity;

import lombok.*;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "labels")
public class Label extends BaseEntity {

    private String name;

}
