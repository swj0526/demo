package core.model.controller;

import net.atomarrow.domains.Domain;
import net.atomarrow.domains.DomainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swj
 * @create 2019-11-08 9:00
 */
@RestController
public class IndexController {
    @Autowired
    /**
     * 自动生成跟修改数据库表
     */
    private DomainUtil domainUtil;
    @RequestMapping("/updateTable")
    public String updateTable() {
        domainUtil.generateAllSingleTable();
        return "success";
    }
}