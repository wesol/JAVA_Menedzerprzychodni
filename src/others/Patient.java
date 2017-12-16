package others;

public class Patient {

	private Integer id;
	private String name;
	private String last;
	private String cellphone;
	private String pesel;

	public Integer getID() {
		return id;
	}

	public void setID(Integer iD) {
		id = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;

	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Patient() {

	}

}
