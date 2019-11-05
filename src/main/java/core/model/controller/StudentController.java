package core.model.controller;

import core.model.bean.PagerBean;
import core.model.bean.ResultBean;
import core.model.bean.StudentBean;
import core.model.bean.StudentScoreBean;
import core.model.service.ScoreService;
import core.model.service.StudentService;
import core.utils.database.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 新增成绩页面
     * @return
     */
    @RequestMapping("/addStudent")
    public String addStudent() {
        return "addStudent";
    }

    /**
     * 学生信息页面
     * @return
     */
    @RequestMapping("/studentList")
    public String studentList() {
        return "studentList";
    }

    /**
     * 添加学生信息
     * @param studentBean
     * @return
     */
    @RequestMapping("/addStudentController")
    @ResponseBody
    public ResultBean addStudentController(StudentBean studentBean) {
        ResultBean resultBean = studentService.addStudent(studentBean);
        return resultBean;

    }

    /**
     * 获取单个学生信息
     * @param studentBean
     * @return
     */
    @RequestMapping("/getStudentController")
    @ResponseBody
    public ResultBean getStudentController(StudentBean studentBean) {
        ResultBean resultBean = studentService.getStudent(studentBean);
        return resultBean;
    }

    /**
     * 学生成绩列表
     * @return
     */
    @RequestMapping("/listStudentScoreController")
    @ResponseBody
    public ResultBean listStudentScoreController() {
        ResultBean resultBean = studentService.listStudentScore();
        return resultBean;
    }

    /**
     * 学生条件查询
     * @param studentScoreBean
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/listConditionController")
    @ResponseBody
    public ResultBean listConditionController(StudentScoreBean studentScoreBean, String page, String limit) {
        ResultBean resultBean = studentService.listCondition(studentScoreBean, page, limit);
        return resultBean;
    }

    /**
     * 修改学生分数信息
     * @param studentScoreBean
     * @return
     */
    @RequestMapping("/modifyStudentScoreController")
    @ResponseBody
    public ResultBean modifyStudentScoreController(StudentScoreBean studentScoreBean) {
        ResultBean resultBean = studentService.modifyStudentScore(studentScoreBean);
        return resultBean;

    }

    /**
     * 删除学生信息
     * @param studentScoreBean
     * @return
     */

    @RequestMapping("/delStudentController")
    @ResponseBody
    public ResultBean delStudentController(StudentScoreBean studentScoreBean) {
        ResultBean resultBean = studentService.delStudentScore(studentScoreBean);
        return resultBean;

    }
}
