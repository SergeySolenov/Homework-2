package tables;

import db.DBConnector;
import db.IDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbsTable implements ITable {
    protected IDBConnector dbConnector;
    private final String tableName;

    public AbsTable(String tableName) {
        dbConnector = new DBConnector();
        this.tableName = tableName;
    }

    public void create(String columns) {
        delete();
        dbConnector.execute(String.format("create table if not exists %s (%s);", this.tableName, columns));
    }

    public void delete() {
        dbConnector.execute(String.format("drop table if exists %s;", this.tableName));
    }

    public void insert(String columns, Object tableObject) {
        dbConnector.execute(String.format("insert into %s (%s) values (%s);", this.tableName,
                columns, tableObject.toString()));
    }

    public ResultSet selectAll(String persons, String tableName1, String tableName2, String tableCol1, String tableCol2,
                               String tableName3, String tableColl2, String tableCol3) {
        return dbConnector.executeQuery(String.format(
                "SELECT %s FROM %s INNER JOIN %s ON %s=%s INNER JOIN %s ON %s = %s;",
                persons, tableName1, tableName2, tableCol1, tableCol2, tableName3, tableColl2, tableCol3)
        );
    }

    public ResultSet indexForGender(String persons, String colSex, String valSex) {
        return dbConnector.executeQuery(String.format("SELECT %s FROM %s WHERE %s = '%s';",
                persons, this.tableName, colSex, valSex));
    }

    public ResultSet countStudents(String conId) {
        return dbConnector.executeQuery(String.format("SELECT COUNT (%s) FROM %s ;",
                conId, this.tableName));
    }

    public void changeCurator(String curUpd, String valUpd, String colCond, String valCond) {
        dbConnector.execute(String.format("UPDATE %s SET %s=%s WHERE %s=%s;", this.tableName,
                curUpd, valUpd, colCond, valCond));
    }

    public ResultSet selectGroupAndCurator(String persons, String personId, String tableName2, String tableCol2) {
        return dbConnector.executeQuery(String.format(
                "SELECT %s FROM %s JOIN %s ON %s.%s = %s.%s;",
                persons, this.tableName, tableName2, this.tableName, personId, tableName2, tableCol2)
        );
    }

    public ResultSet selectStudentsInGroups(String persons, String tableCol, String colRes2,
                                            String tableName2, String tableCol2, String valCond) {
        return dbConnector.executeQuery(String.format(
                "SELECT %s FROM %s WHERE %s = (SELECT %s FROM %s where %s = '%s');",
                persons, this.tableName, tableCol, colRes2, tableName2, tableCol2, valCond)
        );
    }

    public void showResult(ResultSet resultSet) {
        try {
            int count = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i <= count; i++) {
                    stringBuilder.append(resultSet.getString(i)).append(" ");
                    if (i == count) {
                        System.out.println(stringBuilder);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}