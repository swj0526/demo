package core.model.controller;


import core.model.bean.ResultBean;
import org.springframework.stereotype.Service;
/**
 * @author swj
 * @create 2019-11-04 22:18
 */
@Service
public class BaseController {

    public static ResultBean success() {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(true);
        resultBean.setCode("0");
        resultBean.setData(null);
        return resultBean;
    }

    public static ResultBean success(Object data) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(true);
        resultBean.setCode("0");
        resultBean.setData(data);
        return resultBean;
    }
    public static ResultBean success(Object data,int count) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(true);
        resultBean.setCode("0");
        resultBean.setData(data);
        resultBean.setCount(count);
        return resultBean;
    }

    public static  ResultBean failure() {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setCode("-1");
        resultBean.setMsg( "");
        return resultBean;
    }

    public static ResultBean failure(String msg) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setCode("-1");
        resultBean.setMsg( msg);
        return resultBean;
    }

    public static ResultBean failure(int code) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setCode("-1");
        resultBean.setMsg( "");
        return resultBean;
    }

    public static ResultBean failure(int code, String msg) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setCode("-1");
        resultBean.setMsg( msg);
        return resultBean;
    }
}
