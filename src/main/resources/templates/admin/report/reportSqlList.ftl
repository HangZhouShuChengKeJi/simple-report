<#-- 必须引入layout宏定义文件 -->
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
            <table width="100%" border="0" cellspacing="0" cellpadding="0"
                   class="table table-bordered table-hover table-striped">
                <tr>
                    <th align="left" valign="middle">code</th>
                    <th align="left" valign="middle">名称</th>
                    <th align="middle" valign="middle">数据源</th>
                    <th align="middle" valign="middle">调度计划</th>
                    <th align="middle" valign="middle">通知邮箱</th>
                    <th align="middle" valign="middle">数据库状态</th>
                    <th align="middle" valign="middle">系统状态</th>
                    <th align="left" valign="middle">操作</th>
                </tr>
                <#if (dataList?? && dataList?size > 0) >
                    <#list dataList as data>
                        <tr>
                            <td align="left" valign="top">${data.sqlCode!}</td>
                            <td align="left" valign="top">${data.name!}</td>
                            <td align="middle" valign="top">${data.dataSource!}</td>
                            <td align="middle" valign="top">${data.schedulePlan!}</td>
                            <td align="middle" valign="top">${data.noticeEmail!}</td>
                            <td align="middle" valign="top">${data.state!}</td>
                            <td align="middle" valign="top">${(stateMap.get(data.id))!}</td>

                            <td align="left" valign="top">
                                <#if (stateMap.get(data.id))!"" == "NONE" || (stateMap.get(data.id))!"" == "">
                                    <a href="javascript:void(0);" class="s-blue"
                                       onclick="startJob('${data.id!}');">启动</a>
                                </#if>
                                <#if data.state?? && data.state != 0>
                                    <a href="javascript:void(0);" class="s-blue"
                                       onclick="operateJob('${data.sqlCode!}', 3);">停止</a>
                                </#if>
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
</#assign>

<#-- layout 宏里的 pageScript -->
<#assign pageScript in layout>
    <script type="application/javascript">
        $(document).ready(function () {
            // 绑定时间控件

            // 查询按钮
            $("#btnQuery").click(function () {
                $("#pageForm").submit();
            });
            // 新增按钮
            $("#btnAdd").click(function () {
                window.location.href = "/admin/report/editInit.htm";
            });

        });

        // 操作任务
        function operateJob(_jobName, _operateType) {
            var msg = "";
            var _op = "";
            if (_operateType == 1) {
                msg = "确认暂停任务？";
                _op = "暂停";
            } else if (_operateType == 2) {
                msg = "确认恢复任务？";
                _op = "恢复";
            } else if (_operateType == 3) {
                msg = "确认删除任务？";
                _op = "删除";
            }
            if (!confirm(msg)) {
                return false;
            }
            jQuery.ajax({
                type: "post",
                url: "/admin/quartz/operateJob.htm",
                data: "jobName=" + _jobName + "&operateType=" + _operateType,
                success: function (data) {
                    if (data.result == "1") {
                        alert(_op + "成功");
                        window.location.reload();
                    } else {
                        alert(_op + "失败");
                    }
                },
                dataType: "json"
            });
        }

        // 操作任务
        function startJob(_jobId) {
            if (!confirm("确认启动任务")) {
                return false;
            }
            jQuery.ajax({
                type: "post",
                url: "/admin/quartz/startJob.htm",
                data: "jobId=" + _jobId,
                success: function (data) {
                    if (data.result == "1") {
                        alert("启动成功");
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



