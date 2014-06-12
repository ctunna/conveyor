package main;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class View extends JFrame {
	// ... Components
	private JTextField machine_time = new JTextField(15);
	private JTextField since_reset = new JTextField(3);
	private JTextField current_weight = new JTextField(10);
	private JTextField weight_per_second = new JTextField(5);
	private JTextField status = new JTextField(20);
	private JButton resetBtn = new JButton("Reset");

	private Model m_model;

	// ======================================================= constructor
	/** Constructor */
	View(Model model) {
		// ... Set up the logic
		m_model = model;

		// ... Initialize components
		current_weight.setEditable(false);
		machine_time.setEditable(false);
		since_reset.setEditable(false);
		
		// ... Layout the components.
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		content.add(new JLabel("Machine time:"));
		content.add(machine_time);
		content.add(new JLabel("Minutes since reset:"));
		content.add(since_reset);
		content.add(new JLabel("Total weight (lbs):"));
		content.add(current_weight);
		content.add(new JLabel("Weight per second (lbs/sec):"));
		content.add(weight_per_second);
		content.add(new JLabel("Status:"));
		content.add(status);
		content.add(resetBtn);
		// content.add(m_userInputTf);
		// content.add(m_multiplyBtn);
		// content.add(new JLabel("Total"));
		// content.add(m_totalTf);
		// content.add(m_clearBtn);

		// ... finalize layout
		this.setContentPane(content);
		this.pack();

		this.setTitle("Conveyor Monitor");
		// The window closing event should probably be passed to the
		// Controller in a real program, but this is a short example.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void reset() {
		// m_totalTf.setText(INITIAL_VALUE);
	}

	void setStatus(String s){
		status.setText(s);
	}
	
	void setWeight_per_second(String weight){
		weight_per_second.setText(weight);
	}
	
	void setSince_reset(String hours){
		since_reset.setText(hours);
	}
	
	void setMachine_time(String time){
		machine_time.setText(time);
	}
	
	void setCurrent_weight(String newTotal) {
		current_weight.setText(newTotal);
	}

	void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}

	void addResetListener(ActionListener cal) {
		resetBtn.addActionListener(cal);
	}


}
