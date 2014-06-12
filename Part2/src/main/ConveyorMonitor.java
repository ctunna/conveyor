package main;

import java.util.Timer;

public class ConveyorMonitor {
	public static void main(String[] args) {

		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);

		view.setVisible(true);
	}
}
