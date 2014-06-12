package main;

import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.Timer;

public class Controller {
	private Timer ti;
	private Model m_model;
	private View m_view;

	// ========================================================== constructor
	/** Constructor */
	Controller(Model model, View view) {
		m_model = model;
		m_view = view;
		ti = new Timer(1000, new CurrentWeightListener());
		ti.start();

		// ... Add listeners to the view.
		view.addResetListener(new ResetListener());
	}

	class CurrentWeightListener implements ActionListener {
		String status = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DecimalFormat f = new DecimalFormat();
			f.setMaximumFractionDigits(3);
			m_view.setCurrent_weight(f.format(m_model.getCurrent_weight()));
			m_view.setMachine_time(m_model.getMachine_time().toString());
			m_view.setSince_reset(String.valueOf(f.format(m_model
					.getTime_since_reset())));
			m_view.setWeight_per_second(String.valueOf(f.format(m_model
					.getWeight_per_second())));
			
			if(!status.equals(m_model.getStatus())){
				status = m_model.getStatus();
				m_view.setStatus(status);
			}
		}
	}

	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_model.reset();
		}
	}
}