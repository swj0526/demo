package core.model.controller;

import core.model.bean.ResultBean;
import core.model.bean.ScoreBean;
import core.model.bean.StudentBean;
import core.model.bean.StudentScoreBean;
import core.model.service.ScoreService;
import core.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private  StudentService  studentService;

    /**
     * 新增学生成绩页面
     * @return
     */
    @RequestMapping("/addScore")
    public String addScore() {
        return "addScore";
    }

    /**
     * 修改学生成绩页面
     * @param scoreBean
     * @return
     */
    @RequestMapping("/modifyScoreController")
    @ResponseBody
    public ResultBean modifyScoreController(ScoreBean scoreBean) {
        ResultBean resultBean = scoreService.modifyScore(scoreBean);
        return resultBean;
    }
}
