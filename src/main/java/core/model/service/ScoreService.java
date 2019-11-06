package core.model.service;

import core.model.bean.*;
import core.model.domain.TbScore;
import core.utils.database.DBUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService extends BaseService {
    public int add(StudentScoreBean scoreBean){//新增学生成绩(dao)
        String sql= "INSERT INTO tbscore (chinese,english,maths) VALUES ("+ scoreBean.getChinese()
                +","+ scoreBean.getEnglish()+","+ scoreBean.getMaths()+")";
        int update =(int) DBUtils.add_returnId(sql); //并返回id
        return update;

    }
    public int modify(ScoreBean scoreBean) {  //修改学生成绩(dao)
        String sql ="UPDATE tbscore SET chinese = "+scoreBean.getChinese()+",maths ="+scoreBean.getMaths()+"" +
                ", english ="+scoreBean.getEnglish()+" WHERE scoreId = "+scoreBean.getId();
        int update = DBUtils.update(sql);
        return update;

    }
    public ResultBean  addScore(StudentScoreBean scoreBean) { //新增学生成绩(service)
        int add = add(scoreBean);
        if(add!=0){
            return success();
        }else{
            return failure();
        }
    }
    public ResultBean  modifyScore(ScoreBean scoreBean) { //新增学生成绩(service)
        int add = modify(scoreBean);
        if(add!=0){
            return success("成绩修改成功!");
        }else{
            return failure("添加成绩失败!");
        }
    }
    public int del(ScoreBean scoreBean){ //删除学生成绩dao
        String sql = "DELETE FROM tbscore WHERE scoreId ="+scoreBean.getId();
        int update = DBUtils.update(sql);
        return update;
    }
}
