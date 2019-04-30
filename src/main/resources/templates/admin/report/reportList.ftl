<#-- 必须引入 layout 宏定义文件 -->
<#import "../../_common/layout_admin.ftl" as layout >
<#-- 引入分页组件 -->
<#import "../../_common/pagebar.ftl" as pagebar >

<#-- layout 宏里的 title -->
<#assign title="首页" in layout />

<#-- layout 宏里的 pageHeader -->
<#--<#assign pageHeader in layout>-->
<#--<header class="page-header">-->
<#--<h1>Welcome</h1>-->
<#--</header>-->
<#--</#assign>-->

<#-- layout 宏里的 pageBody -->
<#assign pageBody in layout>
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="search_list">
                <ul>
                    <li>
                        <input name="btnQuery" type="button" class="quireBtn" id="btnRefresh" value="刷新字典集" />
                    </li>
                    <li style="float:right">
                        <input type="button" name="btnAdd" id="btnAdd" value="新增报表" class="addqd" />
                    </li>
                </ul>
                <div class="clear"></div>
            </div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0"
                   class="table table-bordered table-hover table-striped">
                <tr>
                    <th align="left" valign="middle">报表名称</th>
                    <th align="left" valign="middle">url访问关键字</th>
                    <th align="left" valign="middle">状态</th>
                    <th align="left" valign="middle">操作</th>
                </tr>
                <#if (dataList?? && dataList?size > 0) >
                    <#list dataList as data>
                        <tr>
                            <td align="left" valign="top">${data.name!}</td>
                            <td align="middle" valign="top">${data.visitKey!}</td>
                            <td align="middle" valign="top">${data.state!}</td>
                            <td align="middle" valign="top">
                                <a href="/admin/report/editInit.htm?reportId=${data.id!}&lastPageNo=${query.pageNo!}" class="s-blue">编辑</a>
                                <a href="javascript:void(0);" class="s-blue"
                                   onclick="goodsDelete('${data.id!}');">删除</a>
                                <a href="/report/${data.visitKey!}.htm" class="s-blue" target="_blank">预览</a>
                            </td>
                        </tr>
                    </#list>
                <#else >
                    <tr>
                        <td height="100" colspan="10" align="center" valign="middle" class="borderN">
                            <div class="no-data">暂无查询记录！</div>
                        </td>
                    </tr>
                </#if>
            </table>
            <#-- 引入 pagebar 的 html 片段-->
            <@pagebar.html query=query />
        </div>
    </div>
</#assign>

<#-- layout 宏里的 pageScript -->
<#assign pageScript in layout>
<#-- 引入 pagebar 的 js 片段-->
    <@pagebar.script />
     <script type="application/javascript">
         $(document).ready(function(){
             // 绑定时间控件
             // 查询按钮
             $("#btnQuery").click(function(){
                 $("#pageForm").submit();
             });
             // 新增按钮
             $("#btnAdd").click(function(){
                 window.location.href = "/admin/report/editInit.htm";
             });

             $("#btnRefresh").click(function(){
                 if(!confirm("确认刷新？")){
                     return false;
                 }
                 jQuery.ajax({
                     type : "post",
                     url : "/admin/rdictInfo/refresh.htm",
                     data : "",
                     success : function(data){
                         if(data.result == "1"){
                             alert("刷新成功");
                         } else {
                             alert(data.message);
                         }
                     },
                     dataType: "json"
                 });
             });
         });
         // 删除按钮
         function goodsDelete(_reportId){
             if(!confirm("确认删除？")){
                 return false;
             }
             jQuery.ajax({
                 type : "post",
                 url : "/admin/report/delete.htm",
                 data : "reportId="+_reportId,
                 success : function(data){
                     if(data.result == "1"){
                         alert("删除成功");
                         window.location.reload();
                     } else {
                         alert(data.message);
                     }
                 },
                 dataType: "json"
             });
         }
     </script>

</#assign>

<#compress >
    <#-- 使用 layout 中定义的 html 宏 -->
    <@layout.html/>
</#compress>



