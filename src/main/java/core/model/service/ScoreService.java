package core.model.service;

import core.model.bean.StudentScoreBean;
import core.model.domain.TbScore;
import core.model.domain.TbStudent;
import net.atomarrow.bean.ServiceResult;
import net.atomarrow.db.parser.Conditions;
import net.atomarrow.db.parser.JdbcParser;
import net.atomarrow.services.Service;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class ScoreService extends Service {

    /**
     * 修改学生成绩service
     *
     * @param score
     * @return
     */
    public ServiceResult modifyScoreService(TbScore score) {
        Conditions conditions = new Conditions(TbScore.class);
        conditions.putEW("id", score.getId());
        modifyWithColumn(conditions, new Serializable[]{"chinese", score.getChinese(), "english", score.getEnglish(),
                "maths", score.getMaths()});
        System.out.println(JdbcParser.getInstance().getSelectHql(conditions));
        return success("成绩修改成功");
    }
    /**
     * 删除学生分数Service
     *
     * @param score
     * @return
     */
    public ServiceResult deleteScoreService(TbScore score) {
        del(score);
        return success("学生信息删除成功!");
    }

}
/* int add = add(scoreBean);
        if (add != 0) {
            return success();
        } else {
            return failure();
        }*//*

        return null;
    }

    public ResultBean modifyScore(ScoreBean scoreBean) { //新增学生成绩(service)
        */
/*int add = modify(scoreBean);
        if(add!=0){
            return success("成绩修改成功!");
        }else{
            return failure("添加成绩失败!");
        }*//*

        return null;
    }

    public int del(ScoreBean scoreBean) { //删除学生成绩dao
        String sql = "DELETE FROM tbscore WHERE scoreId =" + scoreBean.getId();
        int update = DBUtils.update(sql);
        return update;
    }
}
*/
