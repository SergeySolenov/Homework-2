package dataobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Group implements IData {
    private final int id;
    private final String name;
    private final int id_curator;

    public String toString() {
        return String.format("'%s', '%s', '%s'", getId(), getName(), getId_curator());
    }
}

