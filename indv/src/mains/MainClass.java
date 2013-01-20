package mains;

import java.io.IOException;

import domain.*;
import repository.*;
import ui.Console;
import controller.*;

public class MainClass {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
		try {
			StudentsRepo s = new StudentsRepo("studentFile.bin");
	    	GradesRepo g = new GradesRepo("gradeFile.bin");
	    	Ctrl ctrl = new Ctrl(g,s);
			Console c = new Console(ctrl);
			c.run();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
