package dataobj;

public record Curator(int id, String fio) implements IData {
    public String toString() {
        return String.format("'%s', '%s'", id(), fio());
    }

}

