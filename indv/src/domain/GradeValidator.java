package domain;

public class GradeValidator {
    public GradeValidator() {}
    
    public void validate(Grade g) throws RuntimeException {
	if (!g.getDiscipline().matches("^[a-z A-Z]+$")) throw new RuntimeException("Invalid Discipline format!");
	if (g.getGrade()>10 || g.getGrade()<1) throw new RuntimeException("Invalid grade format");
	if (g.getNo()<1) throw new RuntimeException("Invalid number id");
    }
}
