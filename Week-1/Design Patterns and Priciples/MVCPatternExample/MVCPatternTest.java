public class MVCPatternTest {
    public static void main(String[] args) {
        // Step 1: Create the model
        Student student = new Student("Alice", "S101", "A");

        // Step 2: Create the view
        StudentView view = new StudentView();

        // Step 3: Create the controller
        StudentController controller = new StudentController(student, view);

        // Step 4: Display initial student info
        controller.updateView();

        // Step 5: Update model data via controller
        controller.setStudentName("Bob");
        controller.setStudentGrade("B+");

        // Step 6: Display updated student info
        controller.updateView();
    }
}
