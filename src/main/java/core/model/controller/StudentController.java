package core.model.controller;

import core.model.bean.PageBean;
import core.model.bean.ResultBean;
import core.model.bean.StudentScoreBean;
import core.model.domain.TbAdmin;
import core.model.domain.TbScore;
import core.model.domain.TbStudent;
import core.model.service.ScoreService;
import core.model.service.StudentService;
import net.atomarrow.bean.Pager;
import net.atomarrow.bean.ServiceResult;
import net.atomarrow.db.parser.Conditions;
import net.atomarrow.render.Render;
import net.atomarrow.util.excel.ExcelDatas;
import net.atomarrow.util.excel.ExcelFormatListener;
import net.atomarrow.util.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Controller
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 新增学生信息页面
     *
     * @return
     */
    @RequestMapping("/addStudent")
    public String addStudent() {
        return "addStudent";
    }


    /**
     * 学生信息列表页面
     *
     * @return
     */
    @RequestMapping("/studentList")
    public String studentList() {
        return "studentList";
    }

    /**
     * 新增学生信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/addStudentController")
    @ResponseBody
    public ServiceResult addStudentController(TbStudent student) {
        ServiceResult result = studentService.addStudentService(student);
        return result;
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/modifyStudentScoreController")
    @ResponseBody
    public ServiceResult modifyStudentController(TbStudent student, TbScore score) {
        ServiceResult result = studentService.modifyStudentService(student);
        scoreService.modifyScoreService(score);
        return result;
    }

    /**
     * 删除学生信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/deleteStudentScoreController")
    @ResponseBody
    public ServiceResult deleteStudentController(TbStudent student, TbScore score) {
        ServiceResult result = studentService.deleteStudentService(student);
        scoreService.deleteScoreService(score);
        return result;
    }

    /**
     * 返回所有学生信息列表
     */
    @RequestMapping("/listConditionController")
    @ResponseBody
    public ResultBean listConditionController(TbStudent student, TbScore score, PageBean page) {
        List<StudentScoreBean> list = studentService.listConditionService(student, score,page);
        Conditions conditions = new Conditions(TbStudent.class);
        int count = studentService.getCount(conditions);
        return success(list,count);

    }

    /**
     * 导出Excel表格
     *
     * @return
     * @throws Exception
     */

    @RequestMapping("/doExcel")
    @ResponseBody
    public Render doExcel(TbStudent student, TbScore score) {
        Render render = studentService.excelService(student, score);
       /* System.out.println(render.);*/
        return render;
    }
}
