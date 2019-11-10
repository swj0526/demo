
package core.model.service;

import core.model.bean.*;
import core.model.domain.TbAdmin;
import core.model.domain.TbScore;
import core.model.domain.TbStudent;
import net.atomarrow.bean.Pager;
import net.atomarrow.bean.ServiceResult;
import net.atomarrow.db.parser.Conditions;
import net.atomarrow.db.parser.JdbcParser;
import net.atomarrow.render.Render;
import net.atomarrow.services.Service;
import net.atomarrow.util.StringUtil;
import net.atomarrow.util.excel.ExcelDatas;
import net.atomarrow.util.excel.ExcelFormatListener;
import net.atomarrow.util.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class StudentService extends Service {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    public HttpServletRequest req;

    /**
     * 新增学生service
     *
     * @param student
     * @return
     */
    public ServiceResult addStudentService(TbStudent student) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        student.setTime(dateNowStr);
        int studentId = Integer.valueOf(String.valueOf((BigInteger) add(student)));//新增学生信息并返回学生信息id
        //新增学生的时候,在给配套添加学生的成绩,默认都是0
        TbScore score = new TbScore();
        BigInteger scoreId = (BigInteger) add(score);//新增成绩并返回成绩id
        String id = String.valueOf(scoreId);
        student.setScoreId(Integer.valueOf(id));
        //创建Conditions对象
        Conditions conditions = new Conditions(TbStudent.class);
        conditions.putEW("id", studentId);
        modifyWithColumn(conditions, new Serializable[]{"scoreId", id});
        return success("新增成功");
    }

    /**
     * 返回所有学生信息Service
     *
     * @return
     */
    public List<StudentScoreBean> listConditionService(TbStudent student, TbScore score, PageBean page) {
        Conditions conditions = new Conditions(TbStudent.class);
        conditions.setReturnClass(StudentScoreBean.class);
        conditions.setSelectValue("student.id,student.name,student.address,student.time,score.chinese,score.english," +
                "score.maths,grade.gradeName,grade.id AS gradeId");
        conditions.setJoin(" student JOIN  tbscore AS score ON student.scoreId = score.id JOIN tbgrade AS grade" +
                " ON grade.id = student.gradeId ");
        if (!StringUtil.isEmpty(student.getName())) {
            conditions.putLIKE("student.name", student.getName());
        }
        if (student.getGradeId() == null || student.getGradeId() == 0) {
        } else {
            conditions.putEW("grade.id", String.valueOf(student.getGradeId()));
        }
        conditions.putASC("score.chinese");
        System.out.println(JdbcParser.getInstance().getSelectHql(conditions));
        Pager pager = new Pager();
        int pageDB = (page.getPage() - 1) * 10;
        page.setPage(pageDB);
        pager.setCurrentPage(page.getPage());
        pager.setPageSize(page.getLimit());
        List<StudentScoreBean> list = getListByPage(conditions, pager);
        return list;
    }

    /**
     * 返回所有学生信息Service,无分页
     *
     * @return
     */
    public List<StudentScoreBean> listNoPage(TbStudent student, TbScore score) {
        Conditions conditions = new Conditions(TbStudent.class);
        conditions.setReturnClass(StudentScoreBean.class);
        conditions.setSelectValue("student.id,student.name,student.address,student.time,score.chinese,score.english," +
                "score.maths,grade.gradeName,grade.id AS gradeId");
        conditions.setJoin(" student JOIN  tbscore AS score ON student.scoreId = score.id JOIN tbgrade AS grade" +
                " ON grade.id = student.gradeId ");
        if (!StringUtil.isEmpty(student.getName())) {
            conditions.putLIKE("student.name", student.getName());
        }
        if (student.getGradeId() == null || student.getGradeId() == 0) {
        } else {
            conditions.putEW("grade.id", String.valueOf(student.getGradeId()));
        }
        conditions.putASC("score.chinese");
        System.out.println(JdbcParser.getInstance().getSelectHql(conditions));
        List<StudentScoreBean> list = getList(conditions);
        return list;
    }

    /**
     * 修改学生信息Service
     *
     * @param student
     * @return
     */
    public ServiceResult modifyStudentService(TbStudent student) {
        Conditions conditions = new Conditions(TbStudent.class);
        conditions.putEW("id", student.getId());
        modifyWithColumn(conditions, new Serializable[]{"name", student.getName(), "address", student.getAddress(), "gradeId",
                student.getGradeId()});
        System.out.println(JdbcParser.getInstance().getSelectHql(conditions));
        return success("学生信息修改成功");
    }

    /**
     * 返回总和
     * @param conditions
     * @return
     */
    public int getlistCount(Conditions conditions) {
        return getCount(conditions);
    }

    /**
     * 删除学生信息Service
     *
     * @param student
     * @return
     */
    public ServiceResult deleteStudentService(TbStudent student) {
        del(student);
        return success("学生信息删除成功!");
    }

    /**
     * 导出学生信息excel表格
     */
    public Render excelService(TbStudent student, TbScore score) {
        ExcelDatas excelDatas = new ExcelDatas();
        List<StudentScoreBean> list = listNoPage(student, score);
        excelDatas.addStringArray(0, 0, new String[]{"姓名", "地址", "入学时间", "学生年级", "语文成绩", "数学成绩", "英语成绩"});
        excelDatas.addObjectList(1, 0, list, new String[]{"name", "address", "time"
                , "gradeName", "chinese", "maths", "english"});//行,列,集合
        InputStream inputStream = ExcelUtil.exportExcel(excelDatas);
        return Render.renderFile("学生信息表.xls", inputStream);

    }





    /*public int modifyScore(StudentBean studentBean) {//修改学生分数id(dao)
        String sql = "UPDATE tbstudent SET scoreId =" + studentBean.getScoreId() + " WHERE id = " + studentBean.getId();
        int update = DBUtils.update(sql);
        return update;

    }

    public ResultBean getStudent(StudentBean studentBean) { //根据姓名验证学生是否存在(service)
        StudentBean studentBeanDB = get(studentBean);
        if (studentBeanDB == null) {
            return failure("该学生不存在");
        } else {
            int scoreId = studentBeanDB.getScoreId();
            return success(studentBeanDB);
        }
    }*/

    /*public StudentBean get(StudentBean studentBean) { //根据姓名验证学生是否存在(dao)
        String sql = "SELECT * FROM tbstudent WHERE name = '" + studentBean.getName() + "'";
        StudentBean studentBeanDB = null;
        try {
            List<Map<String, Object>> list = DBUtils.getList(sql);
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
        } catch (Exception e) {
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
    }*/

   /* public List<StudentScoreBean> list() {
        String sql = "SELECT t.name,t.address,t.time,c.chinese,c.english,c.maths,g.gradeName  FROM  tbscore AS c RIGHT JOIN tbstudent AS t ON t.scoreId = c.scoreId" +
                "  JOIN tbgrade AS g ON g.gradeId = t.gradeId " +
                "   ORDER BY c.chinese,c.english,c.maths";
        List list = DBUtils.getList(sql);
        List<StudentScoreBean> listStudent = new ArrayList<>();
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
    }*/

    /*public ResultBean modifyStudentScore(StudentScoreBean studentScoreBean) { //修改学生成绩所以信息service
        int i = modifyStudent(studentScoreBean);  //修改学生
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
        int update = DBUtils.update(sql);
        return update;
    }

    public ResultBean delStudentScore(StudentScoreBean studentScoreBean) { //删除学生service
        int i = delStudent(studentScoreBean);//删除学生信息
        if (i != 0) {
            int scoreId = studentScoreBean.getScoreId();
            ScoreBean scoreBean = new ScoreBean();
            scoreBean.setId(scoreId);
            int del = scoreService.del(scoreBean); //删除学生成绩
            if (del != 0) {
                return success();
            }
        }
        return failure();
    }*/

  /*  public int delStudent(StudentScoreBean studentScoreBean) { //删除学生service
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
                int count = DBUtils.getCount("tbstudent");
                return success(list, count);
            } else {
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

    public String doExcle() throws IOException, IllegalAccessException {
        List<StudentScoreBean> allByName = list();
        ExcelBean excelBean = new ExcelBean();
        excelBean.setExcelName("学生信息管理系统导出表");
        excelBean.setSheetName("学生信息表");
        excelBean.setList(allByName);
        String[] title = new String[]{"姓名", "地址", "语文成绩", "数学成绩", "英语成绩", "入学时间", "所在年级"};
        String[] keys = new String[]{"name", "address", "chinese", "maths", "english", "time", "gradeName"};
        excelBean.setTitle(title);
        excelBean.setKeys(keys);
        String contextPath = req.getContextPath();
        excelBean.setPath("E:\\work\\demo\\src\\main\\resources\\static\\file");
        ExcelUtils.createExcel(excelBean);
        String path = "/file/学生信息管理系统导出表.xlsx";

        deleteFile.deleteFile(path);//*

        return path;
    }*/
}

