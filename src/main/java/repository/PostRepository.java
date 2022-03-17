package repository;

import entity.Post;
import javax.persistence.EntityGraph;

public class PostRepository extends BaseRepository<Long, Post> {

    public final EntityGraph<?> WRITER_LABEL_GRAPH = getPostLabelGraph();

    public PostRepository() {
        super(Post.class);
    }

    private EntityGraph<?> getPostLabelGraph() {
        var writerPostGraph = getSessionFactory().openSession().createEntityGraph(Post.class);
        writerPostGraph.addAttributeNodes("labels");
        return writerPostGraph;
    }
}
