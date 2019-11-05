package core.model.controller;

import core.model.bean.ExcelBean;
import core.model.bean.ResultBean;
import core.model.bean.StudentScoreBean;
import core.model.service.ExcelService;
import core.model.service.StudentService;
import core.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @author swj
 * @create 2019-11-04 23:27
 * 导出excel表格
 */
@Controller
public class ExcelController {
    @Autowired
    public ExcelService excelService;

    /**
     * 导出excel表格的功能
     *
     * @return
     */
    @RequestMapping("/toExcel")
    @ResponseBody
    public ResultBean toExcel() {
        System.out.println("excel");
        ResultBean resultBean = excelService.toExcel();
        System.out.println(resultBean.getData());
        return resultBean;
    }
}