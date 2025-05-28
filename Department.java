

public class Department implements Searchable {
    private String name;
    private Student[] students = new Student[10];  
    private int count = 0;                          

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addStudent(String name, String id) {
        if (count < 10) {
            students[count++] = new Student(name, id);
            return true;
        }
        return false;
    }

    public String getAllStudents() {
        if (count == 0) return "No students in " + name;
        StringBuilder result = new StringBuilder(name + " Students:\n");
        for (int i = 0; i < count; i++) {
            result.append(students[i].displayInfo()).append("\n");
        }
        return result.toString();
    }

    public String searchByName(String target) {
        for (int i = 0; i < count; i++) {
            if (students[i].getName().equalsIgnoreCase(target)) {
                return "Found in " + name + ": " + students[i].displayInfo();
            }
        }
        return "No student with name '" + target + "' found in " + name;
    }

    public String searchById(String target) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equals(target)) {
                return "Found in " + name + ": " + students[i].displayInfo();
            }
        }
        return "No student with ID '" + target + "' found in " + name;
    }

    public int getRemainingSlots() {
        return 10 - count;
    }
}


 
