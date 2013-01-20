package main;

import java.io.IOException;

import ui.Gui;
import controller.Controller;
import controller.StorageController;
import repository.DeliveryRepository;
import repository.Repository;

public class MainWithGUI {

    /**
     * @param args
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
	Repository repo = new DeliveryRepository("allmydeliveries.bin");
	Controller ctrl = new StorageController(repo);
	Gui gui = new Gui(ctrl);
	gui.show();
    }

}
