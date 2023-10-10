package dataobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Student implements IData {

    private int id;
    private String fio;
    private String sex;
    private int id_group;

    public String toString() {
        return String.format("'%s', '%s', '%s', '%s'", getId(), getFio(), getSex(), getId_group());
    }
}
