package cs523.SparkWC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.stream.Collectors;


public class Driver
{

	public static void main(String[] args) throws Exception
	{
		
		String csvFilePath = "/home/cloudera/Desktop/staticData/carsData.csv"; // Replace with your CSV file path

		String tableName = "CAR";
		String topic = "newCar";
		HbaseManager hbaseManager = new HbaseManager(tableName);
		hbaseManager.createTable(tableName,Car.getListOfColunms());

		
		startConsumer(topic, hbaseManager);

		Producer producer = new Producer(topic,5000);
		//read the data from he CSV
		List<Car> listOfCars = ReadCSV.readFile(csvFilePath);
		//iterate through it and send it to kafka topic
		long countOfCarsWithYear2018Local = listOfCars.stream()
														.filter(car->car.getAssembly().equals("Local"))
														.filter(car->Integer.valueOf(car.getModelYear())==2019).count();
		
		Helper.printThreadSafeError("<<From Driver>>  countOfCarsWithYear2018Local = " + countOfCarsWithYear2018Local);

		for (Car c : listOfCars) {
			producer.kafkaProducer(c);
        }
	}
	
	public static void startConsumer(String topic, HbaseManager hbaseManager){

		Thread consumerThread = new Thread(() -> {
			try {
		        Integer count = 0;

				Consumer consumer = new Consumer(topic);
				consumer.readCarFromKafka__(topic,hbaseManager, count);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			              
        });
		
		consumerThread.start();
		
	}
}
