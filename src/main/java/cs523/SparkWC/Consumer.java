package cs523.SparkWC;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Consumer {

    
	Consumer(String topic){

		startConsumer();
	}
	
	public void startConsumer(){
		
       
	}
	
	public void closeConnection(){
	}
	
	
	@SuppressWarnings("deprecation")
	public static void readCarFromKafka__(String kafkaTopic, HbaseManager hbaseManager, Integer count) throws InterruptedException {
		// Kafka configuration
        String kafkaBrokers = "localhost:9092"; // Replace with your Kafka broker(s)

        // Kafka consumer configuration
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", kafkaBrokers);
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("group.id", "spark-streaming-consumer-group"); // Change the group ID as needed
//     // Set up Spark Streaming configuration
        SparkConf sparkConf = new SparkConf().setAppName("KafkaSparkStreamingConsumer").setMaster("local[*]");
        JavaStreamingContext streamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(1)); // 5-second batch interval

//     // Create Kafka direct stream
        JavaInputDStream<ConsumerRecord<String, String>> kafkaStream = KafkaUtils.createDirectStream(
                streamingContext,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.<String, String>Subscribe(Collections.singletonList(kafkaTopic), kafkaParams));
//        
//     // Extract the value (JSON data) from Kafka records
        JavaDStream<String> jsonDataStream = kafkaStream.map(ConsumerRecord::value);

        JavaDStream<Car> dataStream = jsonDataStream.map(jsonData -> {

            ObjectMapper objectMapper = new ObjectMapper();
    		Car car = objectMapper.readValue(jsonData, Car.class);
            return car;
        });
        
        
        dataStream.foreachRDD((rdd,time)->{  
        	rdd.filter(car->Integer.valueOf(car.getMileage())<100000)
	        	.foreach(car->{
	        		hbaseManager.saveDataIntoHbase(car,count);
	        	});
	        	

//        	rdd.foreach(car->{
//        		hbaseManager.saveDataIntoHbase(car);
//        	});
        });
        
        streamingContext.start();
        System.err.println("===== Start Streaming =====");
        try {
            streamingContext.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
