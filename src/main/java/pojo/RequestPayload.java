package pojo;

import java.util.List;

public class RequestPayload {
	private String employeeId;
	private String firstName;
	private List<ContactPojo> contact;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setContact(List<ContactPojo> contact) {
		this.contact = contact;
	}

	public List<ContactPojo> getContact() {
		return contact;
	}
}
