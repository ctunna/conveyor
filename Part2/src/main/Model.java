package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.Timer;

public class Model {
	private Conveyor conv;
	private double total_weight;
	private double current_weight;
	private double old_weight;
	private double weight_per_second;
	private String status;
	private Timestamp machine_time;
	private double time_since_reset;
	private long seconds;

	ActionListener pollConveyor;
	private final Timer timer;

	public Model() {
		conv = new Conveyor();
		total_weight = 0;
		old_weight = 0;
		current_weight = 0;
		weight_per_second = 0;
		status = "";
		seconds = 0;
		machine_time = new Timestamp(0);
		time_since_reset = 0;

		pollConveyor = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
		};

		timer = new Timer(1000, pollConveyor);
		timer.start();

	}

	public void run() {
		ConveyorData data = conv.getData();
		total_weight = data.getTotal_weight();
		current_weight = total_weight - old_weight;
		weight_per_second = seconds == 0 ? current_weight : current_weight / seconds;
		status = data.getStatus();
		machine_time = data.getTstamp();
		seconds++;
		time_since_reset = seconds * (1.0d / 60.0d); // convert to minutes
	}

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
		return time_since_reset;
	}

	public void setTime_since_reset(double time_since_reset) {
		this.time_since_reset = time_since_reset;
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
