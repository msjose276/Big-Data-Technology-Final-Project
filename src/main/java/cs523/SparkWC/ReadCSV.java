package cs523.SparkWC;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

	
	public static List<Car> readFile(String csvFilePath){
        List<Car> cars = new ArrayList<>();

        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {

                Car car = new Car();
                
                car.setCompanyName(csvRecord.get(0));
                car.setModelName(csvRecord.get(1));
                car.setPrice(csvRecord.get(2));
                car.setModelYear(csvRecord.get(3));
                car.setLocation(csvRecord.get(4));
            	car.setMileage(csvRecord.get(5));
            	car.setEngineType(csvRecord.get(6));
            	car.setEngineCapacity(csvRecord.get(7))	;
            	car.setColor(csvRecord.get(8));
            	car.setAssembly(csvRecord.get(9));
            	car.setBodyType(csvRecord.get(10));
            	car.setTransmissionType(csvRecord.get(11));
            	car.setRegistrationStatus(csvRecord.get(12));
            	
                cars.add(car);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
     // Now, 'cars' list contains the data from the CSV file as objects.<<From HbaseManager>>
		Helper.printThreadSafeError("<<From ReadCSV>>Printing Cars from the CSV File.... ");
        for (Car car : cars) {
    		Helper.printThreadSafeOut("<<From ReadCSV>>  Name = " + car.getCompanyName() + " - " + car.getModelName());
        }
		Helper.printThreadSafeError("<<From ReadCSV>>Printed Cars from the CSV File............. ");

        return cars;
	}

}
