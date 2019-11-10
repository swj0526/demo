package core.model.controller;

import core.model.domain.TbScore;
import core.model.service.ScoreService;
import core.model.service.StudentService;
import net.atomarrow.bean.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentService studentService;


    /*
     * 新增成绩页面
     * @return
     */
    @RequestMapping("/addScore")
    public String addScore() {
        return "addScore";
    }

}

