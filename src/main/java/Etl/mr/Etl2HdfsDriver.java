package Etl.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import java.io.IOException;


/**
 * @Description TODO
 * @Author dtygfn
 */
public class Etl2HdfsDriver implements Tool {

    private static Logger logger = Logger.getLogger(Etl2HdfsDriver.class);
    private Configuration conf = new Configuration();

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Configuration(),new Etl2HdfsDriver(),args);
        } catch (Exception e) {
            logger.error("执行etl异常",e);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        //运行的时候 yarn jar xxx.jar 包名.类名 -d 2018-11-11
        //如果不给日期，我们就要指定默认日期
        //默认日期指定当前时间前一天的数据
        Configuration conf = getConf();
        Job job = Job.getInstance(conf);
        //设置类路径
        job.setJarByClass(Etl2HdfsDriver.class);
        //设置map相关
        job.setMapperClass(Etl2HdfsMapper.class);
        job.setMapOutputKeyClass(SourceWritable.class);
        job.setMapOutputValueClass(NullWritable.class);
        //设置reduce的数量
        job.setNumReduceTasks(0);

        //设置输入输出的路径
        handleInputOutput(job);
        return job.waitForCompletion(true)?1:0;

    }

    /**
     * 设置输入输出路径
     * @param job
     */
    private void handleInputOutput(Job job)  {

        try {
            FileSystem fs = FileSystem.get(job.getConfiguration());

            //输入路径
            Path inpath = new Path("/phoneproject/answer_paper/part-m-00000");
            Path outpath = new Path("/phoneproject/source");
            if(fs.exists(inpath)){
                FileInputFormat.setInputPaths(job,inpath);
            }else{
                throw new RuntimeException("输入路径不存在"+inpath.toString());
            }
            if(fs.exists(outpath)){
                fs.delete(outpath,true);
            }
            FileOutputFormat.setOutputPath(job,outpath);

        } catch (IOException e) {
            logger.error("设置输入输出路径异常",e);
        }

    }

    @Override
    public void setConf(Configuration conf) {
        conf.addResource("core-site.xml");
        this.conf = conf;
    }

    @Override
    public Configuration getConf() {
        return conf;
    }
}
