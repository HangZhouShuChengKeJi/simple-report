<#-- 必须引入 layout 宏定义文件 -->
<#import "../../_common/layout_admin.ftl" as layout >

<#-- layout 宏里的 title -->
<#assign title="报表编辑" in layout />

<#-- layout 宏里的 pageHeader -->
<#--<#assign pageHeader in layout>-->
<#--<header class="page-header">-->
<#--<h1>Welcome</h1>-->
<#--</header>-->
<#--</#assign>-->
<style>
    table tr{
        margin-bottom: 5px;
        margin-top: 5px;
        height: 30px;
    }
</style>

<#-- layout 宏里的 pageBody -->
<#assign pageBody in layout>
    <div class="panel panel-default">
        <div class="panel-body">
            <form id="saveForm" action="" method="post">
                <input type="hidden" name="id" value="${(rReport.id)!}"/>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="addinfo_list">
                    <tr>
                        <td width="250" align="right" valign="middle">报表名称：</td>
                        <td width="200" align="left" valign="top">
                            <input name="name" type="text" class="listBox" id="name" placeholder=""
                                   value="${(rReport.name)!}"/>
                        </td>
                        <td align="left" valign="top">
                            <span id="nameTips" class="error-tips" style="display:none;">请输入报表名称！</span>&nbsp;
                        </td>
                    </tr>
                    <#--<tr>-->
                    <#--<td width="250" align="right" valign="middle">关联key：</td>-->
                    <#--<td width="200" align="left" valign="top">-->
                    <#--<input name="keyword" type="text" class="listBox" id="keyword" placeholder=""-->
                    <#--value="$!{rReport.keyword}"/>-->
                    <#--</td>-->
                    <#--<td align="left" valign="top"><span id="keywordTips" class="error-tips"-->
                    <#--style="display:none;">请输入表名！</span>&nbsp;-->
                    <#--</td>-->
                    <#--</tr>-->

                    <tr>
                        <td width="250" align="right" valign="middle">url访问关键字：</td>
                        <td width="200" align="left" valign="top">
                            <input name="visitKey" type="text" class="listBox" id="visitKey" placeholder=""
                                   value="${(rReport.visitKey)!}"/>
                        </td>
                        <td align="left" valign="top"><span id="visitKeyTips" class="error-tips"
                                                            style="display:none;">请输入url访问关键字！</span>&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td width="250" align="right" valign="middle">导出功能：</td>
                        <td width="200" align="left" valign="middle">
                            开&nbsp;<input type="radio" name="exportFlag" value="1" ${(rReport?? && rReport.exportFlag?? && 1 == rReport.exportFlag)?then("checked", "")}>
                            关&nbsp;<input type="radio" name="exportFlag" value="0" ${( !(rReport??)|| !(rReport.exportFlag??) || 1 != rReport.exportFlag)?then("checked", "")}>
                        </td>
                        <td align="left" valign="top"></td>
                    </tr>
                    <tr>
                        <td width="250" align="right" valign="middle">sql：</td>
                        <td width="200" align="left" valign="top">
                                    <textarea cols="100" rows="10" name="reportSql"
                                              id="reportSql">${(rReport.reportSql)!}</textarea>
                        </td>
                        <td align="left" valign="top"><span id="reportSqlTips" class="error-tips"
                                                            style="display:none;">请输入sql！</span>&nbsp;
                        </td>
                    </tr>

                    <tr>
                        <td width="250" align="right" valign="middle">查询条件：</td>
                        <td width="60%" align="left" valign="top">

                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="addinfo_list"
                                   id="tab_param">
                                <tr>
                                    <th>参数code</th>
                                    <th>参数名称</th>
                                    <th>参数类型</th>
                                    <th>参数枚举值</th>
                                    <th>是否必须填写</th>
                                    <th></th>
                                </tr>
                                <#if rReport?? && rReport.queryParams?? && (rReport.queryParams?size > 0) >
                                    <#list rReport.queryParams as param>
                                        <tr>
                                            <td>
                                                <input name="paramCode" type="text" class="listBox" id="keyword" placeholder="参数code" value="${param.paramCode!}"/>
                                            </td>
                                            <td>
                                                <input name="paramName" type="text" class="listBox" id="keyword" placeholder="参数名称" value="${param.paramName!}"/>
                                            </td>
                                            <td>
                                                <select name="paramType" id="state" class="listBox">
                                                    <option value="">请选择参数类型</option>
                                                    <option value="0" ${("0" == param.type)?then("selected", "")}>
                                                        字符串
                                                    </option>
                                                    <option value="1" ${("1" == param.type)?then("selected", "")}>
                                                        整型
                                                    </option>
                                                    <option value="2" ${("2" == param.type)?then("selected", "")}>
                                                        时间控件
                                                    </option>
                                                    <option value="3" ${("3" == param.type)?then("selected", "")}>
                                                        下拉框
                                                    </option>
                                                    <option value="4" ${("4" == param.type)?then("selected", "")}>
                                                        时间控件（区间）
                                                    </option>
                                                    <option value="5" ${("5" == param.type)?then("selected", "")}>
                                                        整型（区间）
                                                    </option>
                                                </select>
                                            </td>
                                            <td>
                                                <input name="paramEnum" type="text" class="listBox" id="keyword" placeholder="" value="${param.paramEnum!}"/>
                                            </td>
                                            <td>
                                                <select name="paramIsMust" id="state" class="listBox">
                                                    <option value="">是否必须填写</option>
                                                    <option value="0" ${("0" == param.isMust)?then("selected", "")}>
                                                        否
                                                    </option>
                                                    <option value="1" ${("1" == param.isMust)?then("selected", "")}>
                                                        是
                                                    </option>
                                                </select>
                                            </td>
                                            <td><a href="#" onclick="delRow(this)">删除</a></td>
                                        </tr>
                                    </#list>
                                </#if>
                                <tr>
                                    <td><input name="btnCancel" type="button" class="addqd" value="新增"
                                               onclick="addParamRow()"/></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </table>

                        </td>
                        <td align="left" valign="top"><span id="stateTips" class="error-tips"
                                                            style="display:none;">请输入是否展示！</span>&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td width="250" align="right" valign="middle">展示列：</td>
                        <td width="250" align="left" valign="top">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="addinfo_list"
                                   id="tab_show">
                                <tr>
                                    <th>展示code</th>
                                    <th>展示名称</th>
                                    <th>展示枚举值</th>
                                    <th>类型</th>
                                    <th></th>
                                </tr>
                                <#if rReport?? && rReport.queryShows?? && (rReport.queryShows?size > 0) >
                                    <#list rReport.queryShows as show>
                                        <tr>
                                            <td><input name="showCode" type="text" class="listBox" id="keyword"
                                                       placeholder="展示code" value="${show.code!}"/></td>
                                            <td><input name="showName" type="text" class="listBox" id="keyword"
                                                       placeholder="展示名称" value="${show.name!}"/></td>
                                            <td>
                                                <input name="showEnum" type="text" class="listBox" id="keyword" placeholder="" value="${show.showEnum!}"/>
                                            </td>
                                            <td>
                                                <select name="showType" id="state" class="listBox">
                                                    <option value="">请选择类型</option>
                                                    <option value="0" ${("0" == show.type)?then("selected", "")}>
                                                        字符串
                                                    </option>
                                                    <option value="1" ${("1" == show.type)?then("selected", "")}>
                                                        整型
                                                    </option>
                                                    <option value="2" ${("2" == show.type)?then("selected", "")}>
                                                        时间
                                                    </option>
                                                    <option value="3" ${("3" == show.type)?then("selected", "")}>
                                                        图片
                                                    </option>
                                                </select>
                                            </td>
                                            <td><a href="#" onclick="delRow(this)">删除</a></td>
                                        </tr>
                                    </#list>
                                </#if>
                                <tr>
                                    <td><input name="btnCancel" type="button" class="addqd" value="新增"
                                               onclick="addShowRow()"/></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </table>
                        </td>
                        <td align="left" valign="top"><span id="stateTips" class="error-tips"
                                                            style="display:none;">请输入是否展示！</span>&nbsp;
                        </td>
                    </tr>
                </table>

                <hr>

                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="addinfo_list">
                    <tr>
                        <td width="250" align="right" valign="middle">&nbsp;</td>
                        <td align="left" valign="top">
                            <input name="btnSubmit" type="button" class="tjBtn" id="btnSubmit" value="提交"/>&nbsp;&nbsp;
                            <input name="btnCancel" type="button" class="backBtn" id="btnCancel" value="取消"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</#assign>

<#-- layout 宏里的 pageScript -->
<#assign pageScript in layout>
    <script type="application/javascript" src="/static/js/j_validate.js" ></script>
    <script type="application/javascript">
        $(document).ready(function () {
            // 增加校验
            validateEmptyArr(['name', 'visitKey', 'keyword', 'reportSql']);
            numberInputObj($("#settleRate"));

            // 提交
            $("#btnSubmit").click(function () {

                $("input").blur();
                $("textarea").blur();
                if ($(".error-tips:visible").length > 0) {
                    return false;
                }

                jQuery.ajax({
                    type: "post",
                    url: "/admin/report/editSave.htm",
                    data: $("#saveForm").serialize(),
                    success: function (data) {
                        if (data.result == "1") {
                            alert("保存成功");
                            window.location.href = "/admin/report/reportList.htm?pageNo=${lastPageNo!}"
                        } else {
                            alert(data.message);
                        }
                    },
                    dataType: "json"
                });
            });

            $("#btnCancel").click(function () {
                window.location.href = "/admin/report/reportList.htm?pageNo=${lastPageNo!}"
            });
        });

        //添加行
        function addShowRow() {
            var rowTem = '<tr>' +
                '<td><input name="showCode" type="text" class="listBox" id="keyword" placeholder="展示code" value="" /> </td>' +
                '<td><input name="showName" type="text" class="listBox" id="keyword" placeholder="展示名称" value="" /> </td>' +
                '<td><input name="showEnum" type="text" class="listBox" id="keyword" placeholder="" value="" /> </td>' +
                '<td><select name="showType" id="state" class="listBox"> <option value="" >请选择类型 </option> <option value="0">字符串</option> <option value="1">整型</option><option value="2">时间</option> <option value="3">图片</option></select> </td>' +
                '<td><a href="#" onclick="delRow(this)">删除</a></td>' +
                '</tr>';
            var tr_count = $("#tab_show tr").length - 2;
            var addTr = "#tab_show tr:eq(" + tr_count + ")";
            $(addTr).after(rowTem);
        }

        //添加行
        function addParamRow() {
            var rowTem = '<tr>' +
                '<td><input name="paramCode" type="text" class="listBox" id="keyword" placeholder="参数code" value="" /> </td>' +
                '<td><input name="paramName" type="text" class="listBox" id="keyword" placeholder="参数名称" value="" /> </td>' +
                '<td><select name="paramType" id="state" class="listBox"><option value="" >请选择参数类型 </option><option value="0">字符串</option><option value="1">整型</option> <option value="2">时间控件</option> <option value="3" >下拉框</option><option value="4">时间控件（区间）</option><option value="5">整型（区间）</option></select> </td>' +
                '<td><input name="paramEnum" type="text" class="listBox" id="keyword" placeholder="枚举值" value=""/></td>' +
                '<td><select name="paramIsMust" id="state" class="listBox"><option value="" >是否必须填写 </option><option value="0" #if("0" == "$!{param.isMust}")selected #end>否</option><option value="1" #if("1" == "$!{param.isMust}")selected #end>是</option></select></td>' +
                '<td><a href="#" onclick="delRow(this)">删除</a></td>' +
                '</tr>';
            var tr_count = $("#tab_param tr").length - 2;
            var addTr = "#tab_param tr:eq(" + tr_count + ")";
            $(addTr).after(rowTem);
        }

        //删除行
        function delRow(obj) {
            $($($(obj).parent()).parent()).remove()
        }
    </script>
</#assign>

<#compress >
<#-- 使用 layout 中定义的 html 宏 -->
    <@layout.html/>
</#compress>



