package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.Timer;

/**
 * Model class periodically polls Conveyor for data and updates its relevant
 * fields.
 * 
 * 
 * @author Carson Tunna
 * 
 */
public class Model {
	private Conveyor conv;
	private double total_weight; // Total weight processed
	private double current_weight; // Current weight since reset
	private double old_weight; // Weight value when reset was touched
	private double weight_per_second; // Current weight / seconds elapsed
	private String status; // Status of machine
	private Timestamp machine_time; // Time for machine
	private long seconds; // seconds elapsed since reset
	ActionListener pollConveyor;
	private final Timer timer; // Controls how often we poll

	/**
	 * Model constructor
	 * 
	 * pollConveyor ActionListener is created and calls the recieveData() method
	 * every second.
	 * 
	 */
	public Model() {
		conv = new Conveyor();
		total_weight = 0;
		old_weight = 0;
		current_weight = 0;
		weight_per_second = 0;
		status = "";
		seconds = 0;
		machine_time = new Timestamp(0);

		pollConveyor = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recieveData();
			}
		};

		timer = new Timer(1000, pollConveyor);
		timer.start();

	}

	/**
	 * Gets most up to data data from the Conveyor and stores it in a
	 * ConveyorData object.
	 * 
	 * weight_per_second is calculated here by dividing the current weight by
	 * the seconds that have elpased since the reset button was hit.
	 * 
	 * current_weight is the total_weight processed subtracted by what the
	 * weight was last time the reset button was hit (i.e. old_weight).
	 * 
	 */
	private void recieveData() {
		ConveyorData data = conv.getData();
		total_weight = data.getTotal_weight();
		current_weight = total_weight - old_weight;
		weight_per_second = seconds == 0 ? current_weight : current_weight
				/ seconds;
		status = data.getStatus();
		machine_time = data.getTstamp();
		seconds++;
	}

	/**
	 * Called when reset button is hit.
	 * 
	 */
	public void reset() {
		old_weight = total_weight;
		seconds = 0;
	}

	public Conveyor getConv() {
		return conv;
	}

	public double getCurrent_weight() {
		return current_weight;
	}

	public double getTotal_weight() {
		return total_weight;
	}

	public double getWeight_per_second() {
		return weight_per_second;
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getMachine_time() {
		return machine_time;
	}

	public long getSeconds() {
		return seconds;
	}

	public void setConv(Conveyor conv) {
		this.conv = conv;
	}

	public double getTime_since_reset() {
		return Math.floor(seconds * (1.0d / 60.0d)); // convert to minutes;
	}

	public void setTotal_weight(double total_weight) {
		this.total_weight = total_weight;
	}

	public void setWeight_per_second(double weight_per_second) {
		this.weight_per_second = weight_per_second;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMachine_time(Timestamp machine_time) {
		this.machine_time = machine_time;
	}

	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}

}
