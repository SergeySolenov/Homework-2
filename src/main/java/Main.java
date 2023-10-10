import dataobj.Curator;
import dataobj.Group;
import dataobj.Student;
import db.DBConnector;
import tables.AbsTable;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;


public class Main {
    public static void main(String[] args) {
        AbsTable studentsTable = new StudentsTable();
        AbsTable curatorsTable = new CuratorsTable();
        AbsTable groupsTable = new GroupsTable();

        try {
            studentsTable.create("id INT PRIMARY KEY, fio VARCHAR(50), sex VARCHAR(1), id_group INT");
            String stud = "id, fio, sex, id_group";
            studentsTable.insert(stud, new Student(1, "Mia Thalifa", "W", 1));
            studentsTable.insert(stud, new Student(2, "Ivan Ivanov", "M", 2));
            studentsTable.insert(stud, new Student(3, "Jon Sina", "M", 3));
            studentsTable.insert(stud, new Student(4, "Petr Yan", "W", 1));
            studentsTable.insert(stud, new Student(5, "Margo Robi", "W", 1));
            studentsTable.insert(stud, new Student(6, "Kate Blansh", "W", 3));
            studentsTable.insert(stud, new Student(7, "Zutab Matua", "W", 2));
            studentsTable.insert(stud, new Student(8, "Zoya Yarovizina", "W", 3));
            studentsTable.insert(stud, new Student(9, "Pamela Anderson", "W", 1));
            studentsTable.insert(stud, new Student(10, "Itachi Uchiha", "M", 1));
            studentsTable.insert(stud, new Student(11, "Naruto Uzumaki", "M", 2));
            studentsTable.insert(stud, new Student(12, "Zaraki Kenpachi", "M", 3));
            studentsTable.insert(stud, new Student(13, "Roy Jons", "M", 2));
            studentsTable.insert(stud, new Student(14, "Ben Aflec", "M", 3));
            studentsTable.insert(stud, new Student(15, "Margarita Simonyan", "W", 2));

            groupsTable.create("id INT PRIMARY KEY, name_group VARCHAR(150), id_curator INT");
            String group = "id, name_group, id_curator";
            groupsTable.insert(group, new Group(1, "Konoha", 1));
            groupsTable.insert(group, new Group(2, "Uchiha", 4));
            groupsTable.insert(group, new Group(3, "Otus", 3));

            curatorsTable.create("id INT PRIMARY KEY, fio VARCHAR(50)");
            String curator = "id, fio";
            curatorsTable.insert(curator, new Curator(1, "Jana Dark"));
            curatorsTable.insert(curator, new Curator(2, "Jack Nicoldson"));
            curatorsTable.insert(curator, new Curator(3, "Ivan Ivanov"));
            curatorsTable.insert(curator, new Curator(4, "Sasha Gray"));

            System.out.println("    $ All students, groups, curators $");
            studentsTable.showResult(studentsTable.selectAll("students.fio, groups.name_group, curators.fio",
                    "students","groups", "students.id_group",
                    "groups.id", "curators", "groups.id_curator", "curators.id"));
            System.out.println("    $ Count students $");
            studentsTable.showResult(studentsTable.countStudents("id"));

            System.out.println("    $ All woman $");
            studentsTable.showResult(studentsTable.indexForGender("fio", "sex", "W"));

            System.out.println("    $ Curators to group before $");
            groupsTable.showResult(groupsTable.selectGroupAndCurator(
                    "groups.name_group, curators.fio",
                    "id_curator", "curators", "id"));

            System.out.println("    $ Curator to group after changed $");
            groupsTable.changeCurator("id_curator", "2", "id", "1");
            groupsTable.showResult(groupsTable.selectGroupAndCurator(
                    "groups.name_group, curators.fio",
                    "id_curator", "curators", "id"));

            System.out.println("    $ Students in group $");
            studentsTable.showResult(studentsTable.selectStudentsInGroups(
                    "fio", "id_group", "id", "groups",
                    "name_group", "Konoha"));


        } finally {
            DBConnector.close();
        }
    }
}