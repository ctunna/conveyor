package main;

import java.util.Date;
import java.util.Random;
import java.sql.Timestamp;

/**
 * This class represents a virtual Conveyor belt that generates ConveyorData.
 * 
 * @author carson
 * 
 */
public class Conveyor {
	private ConveyorData data;

	public Conveyor() {
		data = new ConveyorData();
		data.setStatus(getStatusString(Status.NORMAL));
		data.setTotal_weight(0);
		data.setTstamp(new Timestamp(new Date().getTime()));
	}

	/**
	 * Updates values of ConveyorData object that resemble that of a real
	 * conveyor belt.
	 * 
	 * Adds small amount to total_weight Updates timestamp Assigns random status
	 */
	private void update() {
		Random rand = new Random(new Date().getTime());
		data.setTotal_weight(data.getTotal_weight()
				+ (100.0d + ((500.0d - 100.0d) * rand.nextDouble())));
		data.setTstamp((new Timestamp(new Date().getTime())));
		data.setStatus(getStatusString(Status.values()[rand.nextInt(Status
				.values().length)]));
	}

	/**
	 * Interface for applications to get ConveyorData
	 * 
	 * @return updated ConveyorData
	 */
	public ConveyorData getData() {
		update();
		return data;
	}

	/**
	 * Returns one of 4 statuses as a string: NORMAL, WARNING, ERROR, and an
	 * undefined status.
	 * 
	 * @return status of the conveyor belt
	 */
	public String getStatusString(Status s) {
		switch (s) {
		case NORMAL:
			return "Everything is normal";
		case WARNING:
			return "Warning: something might not be OK";
		case ERROR:
			return "Error: something is really wrong";
		default:
			return "Undefined status: " + s.toString();
		}
	}
}
