package domain;

public class StudentValidator {
    public StudentValidator() {}
    
    public void validate(Student s) throws RuntimeException{
	if (!s.getName().matches("^[a-z A-Z]+$")) throw new RuntimeException("Invalid Name format!");
	if ((!s.getPin().matches("^[0-9]+$")) && (s.getPin().length() !=13)) throw new RuntimeException("Invalid PIN format");
	if (s.getNo() < 1 ) throw new RuntimeException("Invalid number");
	if (s.getId() < 1) throw new RuntimeException("Invalid registration id");
    }
}
