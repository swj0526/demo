package core.model.domain;

public class TbScore {
    private int scoreId;
    private int chinese; //语文
    private int english; //英语
    private int maths;  //数学

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int id) {
        this.scoreId = scoreId;
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
