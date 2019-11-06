package core.model.service;

import core.model.bean.*;
import core.utils.database.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.pkcs11.Secmod;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentService extends BaseService {
    @Autowired
    private ScoreService scoreService;


    public ResultBean addStudent(StudentBean studentBean) { //新增学生(service)
        System.out.println("新增学生");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        studentBean.setTime(dateNowStr);
        System.out.println(studentBean.getAddress());
        System.out.println(studentBean.getName());
        System.out.println(studentBean.getGradeId());
        int add = add(studentBean);//新增学生并返回学生的id
        System.out.println(add + "学生id");
        if (add != 0) {
            StudentScoreBean scoreBean = new StudentScoreBean();
            int add1 = scoreService.add(scoreBean);//新增成绩并返回id
            System.out.println(add1);
            if (add1 != 0) {
                studentBean.setId(add);
                studentBean.setScoreId(add1);
                int i = modifyScore(studentBean);
                if (i != 0) {
                    return success();
                } else {
                    return failure("服务器异常,新增学生失败!");
                }
            } else {
                return failure("服务器异常,新增学生失败!");
            }
        } else {
            return failure("服务器异常,新增学生失败!");
        }
    }

    public int add(StudentBean studentBean) {//新增学生(dao)
        String sql = "INSERT INTO tbstudent (name,address,time,gradeId) VALUES ('" + studentBean.getName() + "','" + studentBean.getAddress()
                + "','" + studentBean.getTime() + "'," + studentBean.getGradeId() + ")";
        System.out.println(sql);
        int update = (int) DBUtils.add_returnId(sql);
        return update;

    }

    public int modifyScore(StudentBean studentBean) {//修改学生分数id(dao)
        String sql = "UPDATE tbstudent SET scoreId =" + studentBean.getScoreId() + " WHERE id = " + studentBean.getId();
        System.out.println(sql);
        int update = DBUtils.update(sql);
        return update;

    }

    public ResultBean getStudent(StudentBean studentBean) { //根据姓名验证学生是否存在(service)
        System.out.println("进入service");
        StudentBean studentBeanDB = get(studentBean);
        System.out.println(studentBeanDB);
        if (studentBeanDB == null) {
            System.out.println("学生不存在");
            return failure("该学生不存在");
        } else {
            int scoreId = studentBeanDB.getScoreId();
            System.out.println("分数id" + scoreId);
            return success(studentBeanDB);
        }
    }

    public StudentBean get(StudentBean studentBean) { //根据姓名验证学生是否存在(dao)
        String sql = "SELECT * FROM tbstudent WHERE name = '" + studentBean.getName() + "'";
        System.out.println(sql);
        StudentBean studentBeanDB = null;
        try {
            List<Map<String, Object>> list = DBUtils.getList(sql);
            System.out.println(list);
            if (list.size() != 0) {
                studentBeanDB = new StudentBean();
                Map<String, Object> map = list.get(0);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("name")) {
                        studentBeanDB.setName((String) entry.getValue());
                    }
                    if (entry.getKey().equals("scoreId")) {
                        studentBeanDB.setScoreId((Integer) entry.getValue());
                    }
                }
            }
            System.out.println(studentBeanDB.getScoreId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return studentBeanDB;

    }

    public ResultBean listStudentScore() { //查找所有学生信息(service)
        List<StudentScoreBean> list = list();
        if (list.size() == 0) {
            return failure();
        } else {
            return success(list);
        }
    }

    public List<StudentScoreBean> list() {
        String sql = "SELECT t.name,t.address,t.time,c.chinese,c.english,c.maths,g.gradeName  FROM  tbscore AS c RIGHT JOIN tbstudent AS t ON t.scoreId = c.scoreId" +
                "  JOIN tbgrade AS g ON g.gradeId = t.gradeId " +
                "   ORDER BY c.chinese,c.english,c.maths";
        List list = DBUtils.getList(sql);
        List<StudentScoreBean> listStudent = new ArrayList<>();
        System.out.println(list);
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                StudentScoreBean studentScoreBean = new StudentScoreBean();
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("chinese")) {
                        studentScoreBean.setChinese((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("gradeName")) {
                        studentScoreBean.setGradeName((String) entry.getValue());
                    }
                    if (entry.getKey().equals("address")) {
                        studentScoreBean.setAddress((String) entry.getValue());
                    }
                    if (entry.getKey().equals("maths")) {
                        studentScoreBean.setMaths((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("name")) {
                        studentScoreBean.setName((String) entry.getValue());
                    }
                    if (entry.getKey().equals("english")) {
                        studentScoreBean.setEnglish((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("time")) {
                        studentScoreBean.setTime((String) entry.getValue());
                    }

                }
                listStudent.add(studentScoreBean);
            }

        }
        return listStudent;
    }

    public ResultBean modifyStudentScore(StudentScoreBean studentScoreBean) { //修改学生成绩所以信息service
        int i = modifyStudent(studentScoreBean);  //修改学生
        System.out.println("成绩id:" + studentScoreBean.getScoreId());
        if (i != 0) {
            ScoreBean scoreBean = new ScoreBean();
            scoreBean.setId(studentScoreBean.getScoreId());
            scoreBean.setChinese(studentScoreBean.getChinese());
            scoreBean.setEnglish(studentScoreBean.getEnglish());
            scoreBean.setMaths(studentScoreBean.getMaths());
            int modify = scoreService.modify(scoreBean); //修改成绩
            if (modify != 0) {
                return success("修改成功");
            }
        }
        return failure();
    }

    private int modifyStudent(StudentScoreBean studentScoreBean) { //修改学生dao
        String sql = "UPDATE tbstudent SET " +
                "name = '" + studentScoreBean.getName() + "' , address = '" + studentScoreBean.getAddress() + "' ," +
                "gradeId = " + studentScoreBean.getGradeId() + " WHERE id = " + studentScoreBean.getId();
        System.out.println(sql);
        int update = DBUtils.update(sql);
        return update;
    }

    public ResultBean delStudentScore(StudentScoreBean studentScoreBean) { //删除学生service
        int i = delStudent(studentScoreBean);//删除学生信息
        if (i != 0) {
            int scoreId = studentScoreBean.getScoreId();
            System.out.println("删除1" + scoreId);
            ScoreBean scoreBean = new ScoreBean();
            scoreBean.setId(scoreId);
            int del = scoreService.del(scoreBean); //删除学生成绩
            if (del != 0) {
                return success();
            }
        }
        return failure();
    }

    public int delStudent(StudentScoreBean studentScoreBean) { //删除学生service
        String sql = "DELETE FROM tbstudent WHERE id =" + studentScoreBean.getId();
        int update = DBUtils.update(sql);
        return update;
    }

    public ResultBean listCondition(StudentScoreBean studentScoreBean, String page, String limit) {  //查找学生迷糊查询service
        List<StudentScoreBean> list = list(studentScoreBean, page, limit);
        if (list.size() == 0) {
            return failure();
        } else {
            if (studentScoreBean.getGradeId() == 0 && (studentScoreBean.getName() == null || studentScoreBean.getName() == "")) {
                System.out.println("全部数据");
                int count = DBUtils.getCount("tbstudent");
                return success(list, count);
            } else {
                System.out.println("条件数据");
                int count = list.size();
                return success(list, count);
            }
        }
    }

    public List<StudentScoreBean> list(StudentScoreBean studentScoreBean, String page, String limit) { //模糊查询dao
        String sql = "";
        String name = "";
        if (studentScoreBean.getName() != null) {
            name = studentScoreBean.getName();
        }
        if (studentScoreBean.getGradeId() == 0) {
            sql = "SELECT t.id as studentId,t.name,t.address,t.time,c.chinese,c.english,c.maths,g.gradeName,g.gradeId,c.scoreId FROM  tbscore AS c RIGHT JOIN tbstudent AS t ON t.scoreId = c.scoreId" +
                    "  JOIN tbgrade AS g ON g.gradeId = t.gradeId " +
                    " WHERE t.name like '%" +
                    name + "%'  ORDER BY c.chinese,c.english,c.maths limit " + (Integer.valueOf(page) - 1) * 10 + ", " + limit;
        } else {
            sql = "SELECT t.id as studentId,t.name,t.address,t.time,c.chinese,c.english,c.maths,g.gradeName, g.gradeId,c.scoreId FROM  tbscore AS c RIGHT JOIN tbstudent AS t ON t.scoreId = c.scoreId" +
                    "  JOIN tbgrade AS g ON g.gradeId = t.gradeId " +
                    " WHERE t.name like '%" +
                    name + "%' AND t.gradeId = " + studentScoreBean.getGradeId() + "  ORDER BY c.chinese,c.english,c.maths  limit " + (Integer.valueOf(page) - 1) * 10 + ", " + limit;
        }

        List list = DBUtils.getList(sql);
        List<StudentScoreBean> listStudent = new ArrayList<>();
        System.out.println(list);
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                StudentScoreBean studentScoreBeanDB = new StudentScoreBean();
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("chinese")) {
                        studentScoreBeanDB.setChinese((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("gradeName")) {
                        studentScoreBeanDB.setGradeName((String) entry.getValue());
                    }
                    if (entry.getKey().equals("address")) {
                        studentScoreBeanDB.setAddress((String) entry.getValue());
                    }
                    if (entry.getKey().equals("maths")) {
                        studentScoreBeanDB.setMaths((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("name")) {
                        studentScoreBeanDB.setName((String) entry.getValue());
                    }
                    if (entry.getKey().equals("english")) {
                        studentScoreBeanDB.setEnglish((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("time")) {
                        studentScoreBeanDB.setTime((String) entry.getValue());
                    }
                    if (entry.getKey().equals("id")) {
                        studentScoreBeanDB.setId((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("scoreId")) {
                        studentScoreBeanDB.setScoreId((Integer) entry.getValue());
                    }
                    if (entry.getKey().equals("gradeId")) {
                        studentScoreBeanDB.setGradeId((Integer) entry.getValue());
                    }
                }
                listStudent.add(studentScoreBeanDB);
            }

        }
        return listStudent;
    }
}
