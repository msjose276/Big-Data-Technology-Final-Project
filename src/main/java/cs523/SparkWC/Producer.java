package cs523.SparkWC;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Producer {

	private  Properties properties;
	private KafkaProducer producer ;
	private String topic;
	private long timeToSleep;
	private Random random ;
	private String bootstrapServers;
	
	Producer(String topic, long timeToSleep){
		this.topic = topic;
		this.timeToSleep = timeToSleep;
		random = new Random();
		bootstrapServers = "localhost:9092";
        startProducer();
	}
	
	public void startProducer(){
		properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        
        properties.put("group.id", "your_producer_group_id_123");
		producer = new KafkaProducer(properties);

	}
	public void stopProducer(){
        producer.close();
	}
	

	public RecordMetadata kafkaProducer(Car car) {

        try {

        	ObjectMapper objectMapper = new ObjectMapper();
        	String carJson = objectMapper.writeValueAsString(car);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic,"key", carJson);
            
            Future<RecordMetadata> sendFuture = producer.send(record);
            RecordMetadata metadata = sendFuture.get();
            Helper.printThreadSafeOut("Message sent to partition " + metadata.partition() +
                    " at offset " + metadata.offset());
            Thread.sleep(timeToSleep); // Sleep for 1 second
            return metadata;
        }catch (Exception e) {
          System.err.println("~~~~~~~~~~~~~~~~~~~~~~~ERROR SENDING MESSAGE: " + e.getMessage());
        }
        return null;

    }
	
}
