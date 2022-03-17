package entity;

import listeners.PostStatusAndDateListener;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = "labels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "labels")
@Builder
@Entity(name = "posts")
@EntityListeners(PostStatusAndDateListener.class)
public class Post extends BaseEntity{

    private String content;

    @Column(name = "created")
    private LocalDateTime createdDate;

    @Column(name = "updated")
    private LocalDateTime updatedDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "post_status")
    private PostStatus status;

    @ManyToMany()
    @JoinTable(
            name = "posts_labels",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels = new ArrayList<>();

    public void addLabel(Label label) {
        labels.add(label);
    }
}
