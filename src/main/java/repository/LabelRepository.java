package repository;

import entity.Label;

public class LabelRepository extends BaseRepository<Long, Label> {


    public LabelRepository() {
        super(Label.class);
    }


}
