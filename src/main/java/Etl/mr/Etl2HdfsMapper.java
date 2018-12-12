package Etl.mr;

import Etl.util.JsonUtil;
import Etl.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 进行数据清洗的map类
 * @Author dtygfn
 * @Date: 2018/12/12 14:27
 */
public class Etl2HdfsMapper extends Mapper<LongWritable, Text,SourceWritable, NullWritable> {
    private SourceWritable sourceWritable= new SourceWritable();
    private  static Logger logger = Logger.getLogger(Etl2HdfsMapper.class);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[]  fields = line.split("\\001");
        String id= fields[0];
        String exam_id= fields[1];
        String paper_id= fields[2];
        String class_id= fields[6];
        String class_name= fields[7];
        String examinee_id= fields[3];
        String examinee_name= fields[4];
        String examinee_num = fields[5];
        String start_date= fields[8];
        String exam_time= fields[9];
        String stop_date= fields[10];
        String objective_mark= fields[11];
        String subject_mark= fields[12];
        String check_state = fields[14];
        String objective_answer_json = fields[16];
        String subject_answer_json = fields[17];
        if (StringUtils.isEmpty(id)||StringUtils.isEmpty(paper_id)||StringUtils.isEmpty(exam_id)){
           logger.info("id或考试id或试题id不能为null");
        }
        String subject_marks = "0";
        String studentname = MD5Util.getMD5(examinee_name);
        Map<Integer,Double> jsonmap = new HashMap<>();
        jsonmap = JsonUtil.getJsonmap(jsonmap,objective_answer_json);
        logger.info(line);
        if (check_state.equals("1")){
            jsonmap = JsonUtil.getJsonmap(jsonmap,subject_answer_json);
            subject_marks = subject_mark;
        }
        for (Map.Entry<Integer,Double> entry:jsonmap.entrySet()) {
            sourceWritable.setId(Integer.parseInt(id));
            sourceWritable.setExam_id(Integer.parseInt(exam_id));
            sourceWritable.setPaper_id(Integer.parseInt(paper_id));
            sourceWritable.setClass_id(Integer.parseInt(class_id));
            sourceWritable.setClass_name(class_name);
            sourceWritable.setExaminee_id(Integer.parseInt(examinee_id));
            sourceWritable.setExaminee_name(studentname);
            sourceWritable.setExaminee_num(examinee_num);
            sourceWritable.setStart_date(start_date);
            sourceWritable.setExam_time(exam_time);
            sourceWritable.setStop_date(stop_date);
            sourceWritable.setQuestion_id(entry.getKey());
            sourceWritable.setScore(entry.getValue());
            sourceWritable.setObjective_mark(Integer.parseInt(objective_mark));
            sourceWritable.setSubject_mark(Integer.parseInt(subject_marks));
            context.write(sourceWritable,NullWritable.get());
        }

    }
}
