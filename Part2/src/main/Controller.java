package main;

import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.Timer;

/**
 * Controller in MVC pattern
 * 
 * @author Carson Tunna
 * 
 */
public class Controller {
	private Timer ti; // Controls how often we update weight, time, etc.
	private Model m_model;
	private View m_view;

	/**
	 * Constructor for MVC
	 * 
	 * @param model
	 * @param view
	 */
	public Controller(Model model, View view) {
		if(model == null || view == null){
			throw new NullPointerException("Constructor passed null pointer");
		}
	
		m_model = model;
		m_view = view;
		ti = new Timer(1000, new DataListener());
		ti.start();

		// ... Add listeners to the view.
		view.addResetListener(new ResetListener());
	}

	/**
	 * Data listener calls actionPerformed every time ti ticks. This sets
	 * the values in the UI to the most up to date data from the conveyor.
	 * 
	 */
	class DataListener implements ActionListener {
		String status = "";

		@Override
		public void actionPerformed(ActionEvent e) {

			DecimalFormat f = new DecimalFormat();
			f.setMaximumFractionDigits(2);
			m_view.setCurrent_weight(f.format(m_model.getCurrent_weight()));
			m_view.setMachine_time(m_model.getMachine_time().toString());
			m_view.setSince_reset(String.valueOf(f.format(m_model
					.getTime_since_reset())));
			m_view.setWeight_per_second(String.valueOf(f.format(m_model
					.getWeight_per_second())));

			if (!status.equals(m_model.getStatus())) {
				status = m_model.getStatus();
				m_view.setStatus(status);
			}
		}
	}

	/**
	 * Listens for reset button click.
	 * 
	 */
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_model.reset();
		}
	}
}