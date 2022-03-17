package listeners;

import entity.Post;
import entity.PostStatus;
import javax.persistence.*;
import java.time.LocalDateTime;

public class PostStatusAndDateListener {

    @PrePersist
    public void setCreatedStatus(Post post) {
        post.setStatus(PostStatus.CREATED);
        post.setCreatedDate(LocalDateTime.now());
        post.setUpdatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void setUpdatedStatus(Post post) {
        post.setStatus(PostStatus.UNDER_REVIEW);
        post.setUpdatedDate(LocalDateTime.now());
    }

    @PreRemove
    public void setDeletedStatus(Post post) {
        post.setStatus(PostStatus.DELETED);
    }
}
