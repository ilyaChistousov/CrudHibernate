package repository;

import entity.Writer;
import javax.persistence.EntityGraph;


public class WriterRepository extends BaseRepository<Long, Writer> {

    public final EntityGraph<?> WRITER_POST_GRAPH = getWriterPostGraph();

    public WriterRepository() {
        super(Writer.class);
    }

    private  EntityGraph<?> getWriterPostGraph() {
        EntityGraph<?> writerPostGraph = getSessionFactory().openSession().createEntityGraph(Writer.class);
        writerPostGraph.addAttributeNodes("posts");
        return writerPostGraph;
    }
}
