/**
 * 
 */
package domain;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author blink
 *
 */
public class TestDomain extends TestCase {
    
    public void testGrade() {
	Grade g1 = new Grade("Info", 7, 1243);
	assertEquals(g1.getDiscipline(),"Info");
	
	Grade g2 = new Grade("Mate", 7, 23432);
	assertEquals(g2.getGrade(), g1.getGrade());
	
	g2.setDiscipline("History");
	assertEquals(g2.getDiscipline(),"History");
	
    }

    public void testStudent() {
	Student s1 = new Student("Bill","8234945393128", 421,22);
	assertEquals(s1.getId(), 421);
	
	Student s2 = new Student();
	assertEquals(s2.getName(), null);
    };
        
}
