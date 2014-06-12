package main;

/**
 * Main method for MVC pattern.
 * 
 * @author carson
 *
 */
public class ConveyorMonitor {
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);

		view.setVisible(true);
	}
}
