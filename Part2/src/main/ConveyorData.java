package main;

import java.sql.Timestamp;

/**
 * Conveyor data that hypothetically would be serialized and sent through a
 * socket.
 * 
 * 
 * @author Carson Tunna
 * 
 */
public class ConveyorData implements java.io.Serializable {
	private double total_weight;
	private Timestamp tstamp;
	private String status;

	public double getTotal_weight() {
		return total_weight;
	}

	public Timestamp getTstamp() {
		return tstamp;
	}

	public String getStatus() {
		return status;
	}

	public void setTotal_weight(double total_weight) {
		this.total_weight = total_weight;
	}

	public void setTstamp(Timestamp tstamp) {
		this.tstamp = tstamp;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}