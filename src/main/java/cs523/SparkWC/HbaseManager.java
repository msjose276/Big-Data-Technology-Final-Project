package cs523.SparkWC;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import java.util.Random;

public class HbaseManager implements Serializable{
	
	private String tableName;
//	private int tableSize;
    AtomicInteger tableSize = new AtomicInteger(0);

	public HbaseManager(String tableName) {
		super();
		this.tableName = tableName;
//		this.tableSize = 1;
	}
	
	public void createTable(String tableName, List<String> list) throws IOException{
		Configuration config = HBaseConfiguration.create();

		 
		try 
		{
			Connection connection = ConnectionFactory.createConnection(config);
			Admin admin = connection.getAdmin();
					
			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
			
			if (admin.tableExists(table.getTableName()))
			{
				admin.disableTable(table.getTableName());
				admin.deleteTable(table.getTableName());
				Helper.printThreadSafeError("<<From HbaseManager>>  Deleting table.... ");

			}
			Helper.printThreadSafeError("<<From HbaseManager>>  Creating table.... ");
			for (String col: list) {
				table.addFamily(new HColumnDescriptor(col));
				Helper.printThreadSafeOut("<<From HbaseManager>>  Creating colunm = "+col);
	        }
			admin.createTable(table);
			Helper.printThreadSafeError("<<From HbaseManager>>  Table Created  \n");
		}
		catch(Exception e){
			Helper.printThreadSafeOut("----"+e.getMessage());
		}
	}
	
	public void saveDataIntoHbase(Car car,Integer count) throws IOException{
		Configuration config = HBaseConfiguration.create();

		try 
		{
			Connection connection = ConnectionFactory.createConnection(config);
			Admin admin = connection.getAdmin();
			
			Table t = connection.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();

//	        // Use the scan to count rows
//	        int rowCount = 0;
//	        try {
//	            Result result;
//	            while ((result = t.getScanner(scan).next()) != null) {
//	                rowCount++;
//	            }
//	        } finally {
//	            // Close the table to release resources
////	            table.close();
//	        }
	        count = count +1;
	        Random random = new Random();
	        int randomNumber = random.nextInt(5000) + 1;
	        
			Put put1 = new Put(Bytes.toBytes(String.valueOf(randomNumber)));

			for (Pair<String,String> pair : car.getListOfPairKeyValue()) {
				put1.addColumn(Bytes.toBytes(pair.getKey()), Bytes.toBytes(pair.getKey()), Bytes.toBytes(pair.getValue()));
	        }
			
			t.put(put1);
//			tableSize++;
			Helper.printThreadSafeError(randomNumber+" <<From HbaseManager>> Data inserted : " + car.toString());
		}
		catch(Exception e){
			Helper.printThreadSafeError("<<From HbaseManager>> "+e.getMessage());
		}
	}
	 
}
