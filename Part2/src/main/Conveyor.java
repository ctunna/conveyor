package main;

import java.util.Date;
import java.util.Random;
import java.sql.Timestamp;

public class Conveyor {
	private ConveyorData data = new ConveyorData();

	public Conveyor() {
		data.setStatus(getStatusString(Status.NORMAL));
		data.setTotal_weight(0);
		data.setTstamp(new Timestamp(new Date().getTime()));
	}

	private void update() {
		Random rand = new Random(new Date().getTime());
		data.setTotal_weight(data.getTotal_weight()
				+ (100.0d + ((500.0d - 100.0d) * rand.nextDouble())));
		data.setTstamp((new Timestamp(new Date().getTime())));
		data.setStatus(getStatusString(Status.values()[rand.nextInt(Status
				.values().length)]));
	}

	public ConveyorData getData() {
		update();
		return data;
	}

	public enum Status {
		NORMAL, WARNING, ERROR
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
