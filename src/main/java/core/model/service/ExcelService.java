package core.model.service;

import core.model.bean.ExcelBean;
import core.model.bean.ResultBean;
import core.model.bean.StudentScoreBean;
import core.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @author swj
 * @create 2019-11-04 23:29
 */
@Service
public class ExcelService extends BaseService{
    @Autowired
    public StudentService studentService;
    @Autowired
    public HttpServletRequest req;

    public String toExcel(){
        List<StudentScoreBean> allByName = studentService.list();
        ExcelBean excelBean = new ExcelBean();
        excelBean.setExcelName("学生信息管理系统导出表");
        excelBean.setSheetName("学生信息表");
        excelBean.setList(allByName);
        String[] title = new String[]{"姓名", "地址", "语文成绩", "数学成绩", "英语成绩","入学时间","所在年级"};
        excelBean.setTitle(title);
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        excelBean.setPath("E:\\work\\demo\\src\\main\\resources\\static");
        Boolean excel1 = ExcelUtils.createExcel(excelBean);
        String path = "学生信息管理系统导出表.xlsx";
        return path;

    }
}