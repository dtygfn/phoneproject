package Etl.mr;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Description TODO
 * @Author dtygfn
 * @Date: 2018/12/12 11:02
 */
public class SourceWritable implements Writable {
    private int id;//
    private int exam_id;//考试id
    private int paper_id;//试题id
    private int class_id;//班级班号
    private String class_name;//班级名称
    private int  examinee_id;//考号
    private String examinee_name;//姓名
    private String examinee_num;//学号
    private String start_date;//开考时间
    private String exam_time;//已考时间
    private String stop_date;//交卷时间
    private int question_id;//题目id
    private double score;//题目分数
    private int objective_mark;//客观成绩
    private int subject_mark;//主观成绩

    public SourceWritable() {
    }

    public SourceWritable(int id, int exam_id, int paper_id, int class_id, String class_name, int examinee_id, String examinee_name, String examinee_num, String start_date, String exam_time, String stop_date, int question_id, double score, int objective_mark, int subject_mark) {
        this.id = id;
        this.exam_id = exam_id;
        this.paper_id = paper_id;
        this.class_id = class_id;
        this.class_name = class_name;
        this.examinee_id = examinee_id;
        this.examinee_name = examinee_name;
        this.examinee_num = examinee_num;
        this.start_date = start_date;
        this.exam_time = exam_time;
        this.stop_date = stop_date;
        this.question_id = question_id;
        this.score = score;
        this.objective_mark = objective_mark;
        this.subject_mark = subject_mark;
    }

    public String getExaminee_num() {
        return examinee_num;
    }

    public void setExaminee_num(String examinee_num) {
        this.examinee_num = examinee_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getExaminee_id() {
        return examinee_id;
    }

    public void setExaminee_id(int examinee_id) {
        this.examinee_id = examinee_id;
    }

    public String getExaminee_name() {
        return examinee_name;
    }

    public void setExaminee_name(String examinee_name) {
        this.examinee_name = examinee_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getStop_date() {
        return stop_date;
    }

    public void setStop_date(String stop_date) {
        this.stop_date = stop_date;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getObjective_mark() {
        return objective_mark;
    }

    public void setObjective_mark(int objective_mark) {
        this.objective_mark = objective_mark;
    }

    public int getSubject_mark() {
        return subject_mark;
    }

    public void setSubject_mark(int subject_mark) {
        this.subject_mark = subject_mark;
    }

    @Override
    public String toString() {
        return id + + '\u0001'+
                exam_id +'\u0001'+
                paper_id +'\u0001'+
                class_id +'\u0001'+
                class_name + '\u0001'+
                examinee_id +'\u0001'+
                examinee_name + '\u0001'+
                examinee_num +'\u0001'+
                start_date + '\u0001'+
                exam_time + '\u0001'+
                stop_date + '\u0001'+
                question_id +'\u0001'+
                score +'\u0001'+
                objective_mark +'\u0001'+
                subject_mark +'\u0001';
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeInt(exam_id);
        out.writeInt(paper_id);
        out.writeInt(class_id);
        out.writeUTF(class_name);
        out.writeInt(examinee_id);
        out.writeUTF(examinee_name);
        out.writeUTF(examinee_num);
        out.writeUTF(start_date);
        out.writeUTF(exam_time);
        out.writeUTF(stop_date);
        out.writeInt(question_id);
        out.writeDouble(score);
        out.writeInt(objective_mark);
        out.writeInt(subject_mark);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id=in.readInt();
        this.exam_id=in.readInt();
        this.paper_id=in.readInt();
        this.class_id=in.readInt();
        this.class_name=in.readUTF();
        this.examinee_id=in.readInt();
        this.examinee_name=in.readUTF();
        this.examinee_num =in.readUTF();
        this.start_date=in.readUTF();
        this.exam_time=in.readUTF();
        this.stop_date=in.readUTF();
        this.question_id=in.readInt();
        this.score=in.readDouble();
        this.objective_mark=in.readInt();
        this.subject_mark=in.readInt();
    }
}
