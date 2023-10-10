package tables;


import java.sql.ResultSet;

public interface ITable {

    void create(String columns);
    void delete();
    void insert(String columns, Object tableObject);
    ResultSet selectAll(String persons, String tableName1, String tableName2, String tableCol1, String tableCol2,
                               String tableName3, String tableColl2, String tableCol3);
    ResultSet indexForGender(String persons, String colSex, String valSex);
    ResultSet countStudents(String conId);
    void changeCurator(String curUpd, String valUpd, String colCond, String valCond);

}

