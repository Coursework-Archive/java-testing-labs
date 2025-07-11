package java.com.pluralsight.bddfundamentals.airport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Flight {

	private String id;
	List<Passenger> passengersList = new ArrayList<Passenger>();

	public Flight(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public List<Passenger> getPassengersList() {
		return Collections.unmodifiableList(passengersList);
	}

	public abstract boolean addPassenger(Passenger passenger);

	public abstract boolean removePassenger(Passenger passenger);

}
