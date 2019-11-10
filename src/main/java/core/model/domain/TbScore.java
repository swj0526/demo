package core.model.domain;

import net.atomarrow.domains.Domain;
import org.springframework.stereotype.Component;

@Component
public class TbScore extends Domain {
    private Integer id;
    private int chinese; //语文
    private int english; //英语
    private int maths;  //数学

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMaths() {
        return maths;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }
}
