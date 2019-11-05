<div class="layui-header">
    <div class="layui-logo">学生信息管理系统</div>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                <#if Session["name"]?exists>
                   <#-- //存在就显示其属性值-->
                 <#--   //Session["admin"]为取出该session对象-->
                    ${Session["name"]}
                </#if>
            </a>

        </li>
        <li class="layui-nav-item"><a href="/logout">注销</a></li>
    </ul>
</div>