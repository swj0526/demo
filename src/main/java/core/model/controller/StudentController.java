package core.model.controller;

import core.model.bean.PagerBean;
import core.model.bean.ResultBean;
import core.model.bean.StudentBean;
import core.model.bean.StudentScoreBean;
import core.model.domain.TbAdmin;
import core.model.service.ScoreService;
import core.model.service.StudentService;
import core.utils.database.DBUtils;
import net.atomarrow.render.Render;
import net.atomarrow.util.excel.ExcelDatas;
import net.atomarrow.util.excel.ExcelFormatListener;
import net.atomarrow.util.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 导出excle表格
     * @return
     * @throws Exception
     */
    @RequestMapping("/doExcel")
    @ResponseBody
    public Render doExcel() throws Exception {
        ExcelDatas excelDatas = new ExcelDatas();
        List<TbAdmin> list = new ArrayList<>();
        list.add(new TbAdmin(1,"12","12"));
        list.add(new TbAdmin(1,"1233","12233"));
        excelDatas.addStringArray(0,0,new String[]{"编号","用户名","密码"});
        excelDatas.addObjectList(1,0,list,new String[]{"id","userName","password"});//行,列,集合

        InputStream inputStream = ExcelUtil.exportExcel(excelDatas);
      /*ExcelUtil.getListFromExcel("123", TbAdmin.class, new String[]{}, new boolean[]{}, 1, new ExcelFormatListener() {
          @Override
          public Object changeValue(String fieldName, Object fieldValue, int currentRow, int currentCol) {
              if(fieldName.equals("sex")){
                  if((Integer) fieldValue==1){
                      return "男";
                  }
              }
              return null;
          }
      });*/
        return Render.renderFile("学生信息表.xls",inputStream);
        /* return studentService.doExcle();*/
    }
}
