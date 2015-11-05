package com.skangyam.hadoop.mapreduce.MultiInputPathsMap;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MultiInputPathMap1 extends Mapper<LongWritable, Text, Text, Text>
{
	@Override
    protected void map(LongWritable key, Text value, Context context) 
              throws IOException, InterruptedException
    {
        context.write(new Text("FirstMap"), value);
    }
}
