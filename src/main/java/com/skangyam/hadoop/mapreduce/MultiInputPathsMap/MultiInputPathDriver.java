package com.skangyam.hadoop.mapreduce.MultiInputPathsMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MultiInputPathDriver extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		if (args.length != 3) {
			System.out.printf("Usage: %s [generic options] "
					+ "<input1 dir> <input2 Directory> <output dir>\n",
					getClass().getSimpleName());
			return -1;
		}
		
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(MultiInputPathDriver.class);
		job.setJobName(this.getClass().getName());
		
		Path input1 = new Path(args[0]);
		Path input2 = new Path(args[1]);
		
		MultipleInputs.addInputPath(job, input1, TextInputFormat.class, MultiInputPathMap1.class);
		MultipleInputs.addInputPath(job, input2, TextInputFormat.class, MultiInputPathMap2.class);
		
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		job.setNumReduceTasks(0);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		while (job.waitForCompletion(true)){
			return 1;
		}
			
		
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new MultiInputPathDriver(), args);
		System.exit(exitCode);
	}

}
