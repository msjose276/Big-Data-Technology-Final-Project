Project Name: static Analysis with Spark Streaming, HBase, kafka

Introduction
This project demonstrates a data pipeline for static data analysis (csv data), streaming data using kafka, analyzing with spark streaming and storing in HBase. By following the steps outlined in this README, you will be able to set up, configure, and run a staic data analysis system using Kafka as the streaming data platform.

Prerequisites
Before you begin, make sure you have the following prerequisites installed and configured:

Apache Spark
Apache Hadoop
HBase
Apache Kafka


Project Structure:
There are 3 parts in this project:

Data Streaming Application: reading static data and streaming with kafka.

Data consuming and analysis application: consuming the kafka messages, streaming with spark streaming and analyzing.

HBase Integration: Storing processed data in HBase.

Getting Started:
Part 1: Data Streaming Application:
-start a kafka broker and create a topic.
-read the static data (csv) file with java application.
-send the object json as kafka events in kafka topic.

Part 2: HBase Integration:
-read the kafka messages through spark streaming data and send it to Hbase 
-Do analysis about the cars that have less then 100,000 miles and another one that is local and was build in 2018
-generate table
-insert data
-use the web hbase UI to verify the data

Part 3: Data consuming and analysis application:
-consume events from the kafka topic.
-stream the data using spark streaming
-Do required analysis
-Do some experiments with Impala


