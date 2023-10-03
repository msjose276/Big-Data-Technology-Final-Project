package cs523.SparkWC;

import java.util.ArrayList;
import java.util.List;

public class Car {

	String companyName;
	String modelName	;
	String price	;
	String modelYear	;
	String location	;
	String mileage	;
	String engineType	;
	String engineCapacity	;
	String color	;
	String assembly	;
	String bodyType	;
	String transmissionType	;
	String registrationStatus;
	

	public Car() {
	}
	
	public Car(String companyName, String modelName) {
		super();
		this.companyName = companyName;
		this.modelName = modelName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getModelYear() {
		return modelYear;
	}
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public String getEngineCapacity() {
		return engineCapacity;
	}
	public void setEngineCapacity(String engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAssembly() {
		return assembly;
	}
	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public String getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	public String getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	@Override
	public String toString() {
		return "Car [companyName=" + companyName + ", modelName=" + modelName
				+ ", price=" + price + ", modelYear=" + modelYear
				+ ", location=" + location + ", mileage=" + mileage
				+ ", engineType=" + engineType + ", engineCapacity="
				+ engineCapacity + ", color=" + color + ", assembly="
				+ assembly + ", bodyType=" + bodyType + ", transmissionType="
				+ transmissionType + ", registrationStatus="
				+ registrationStatus + "]";
	}
	

	public List<Pair> getListOfPairKeyValue(){
		
		List<Pair> list = new ArrayList<>();
		
		list.add( new Pair<>("companyName",companyName));
		list.add( new Pair<>("modelName",modelName))	;
		list.add( new Pair<>("price",price))	;
		list.add( new Pair<>("modelYear",modelYear))	;
		list.add( new Pair<>("location",location))	;
		list.add( new Pair<>("mileage",mileage))	;
		list.add( new Pair<>("engineType",engineType))	;
		list.add( new Pair<>("engineCapacity",engineCapacity))	;
		list.add( new Pair<>("color",color))	;
		list.add( new Pair<>("assembly",assembly))	;
		list.add( new Pair<>("bodyType",bodyType))	;
		list.add( new Pair<>("transmissionType",transmissionType))	;
		list.add( new Pair<>("registrationStatus",registrationStatus));
		return list;
	}
	
	public static List<String> getListOfColunms(){
		
		List<String> list = new ArrayList<>();
		
		list.add( "companyName");
		list.add( "modelName")	;
		list.add( "price")	;
		list.add( "modelYear")	;
		list.add( "location")	;
		list.add( "mileage")	;
		list.add( "engineType")	;
		list.add( "engineCapacity")	;
		list.add( "color")	;
		list.add( "assembly")	;
		list.add( "bodyType")	;
		list.add( "transmissionType")	;
		list.add( "registrationStatus");
		
		return list;
	}

}
