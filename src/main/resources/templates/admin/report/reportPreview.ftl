<#-- 必须引入 layout 宏定义文件 -->
<#import "../../_common/layout_report.ftl" as layout >
<#-- 引入分页组件 -->
<#import "../../_common/pagebar.ftl" as pagebar >

<#-- layout 宏里的 title -->
<#assign title="${reportName}" in layout />

<#-- layout 宏里的 pageHeader -->
<#--<#assign pageHeader in layout>-->
<#--<header class="page-header">-->
<#--<h1>Welcome</h1>-->
<#--</header>-->
<#--</#assign>-->

<style type="text/css">
    #pageForm ul {
        list-style: none;
    }
    #pageForm li {
        display: inline;
        float:left;
        margin-left: 5px;
        margin-bottom: 10px;
    }

    .wrap{
        max-width:80px;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
    }
</style>
<#-- layout 宏里的 pageBody -->
<#assign pageBody in layout>
<div class="panel panel-default">
    <div class="panel-body">
        <form id="pageForm" name="pageForm" method="post" action="/report/${visitKey}.htm">
            <ul >
                <#if queryColumnList?? && (queryColumnList?size > 0)>
                    <#list queryColumnList as queryCol>
                        <!-- 整型区间-->
                        <#if queryCol.type == "5">
                            <li >
                                <#assign _startInteger =(queryCol.paramCode + "_start")>
                                <#assign _endInteger =(queryCol.paramCode + "_end")>
                                ${queryCol.paramName}：
                                <input name="${_startInteger}" numberFlag="1" type="text" class="qdBox " style="width:100px"   value="${(paramValueMap[("${_startInteger}")])!}" /> -
                                <input name="${_endInteger}" numberFlag="1" type="text" class="qdBox" style="width:100px"    value="${(paramValueMap[("${_endInteger}")])!}"/>
                            </li>
                        <!-- 时间区间-->
                        <#elseif queryCol.type == "4">
                            <#assign _startDate =(queryCol.paramCode + "_start")>
                            <#assign _endDate =(queryCol.paramCode + "_end")>
                            <li>${queryCol.paramName}：
                                <input name="${_startDate}" type="text" class="qdBox" style="width:100px" id="startDate_${queryCol.paramCode}" readonly="readonly" value="${(paramValueMap[("${_startDate}")])!}"/>
                                <img alt="" align="absmiddle"  columnkey="startDate_${queryCol.paramCode}" src="/static/common/JSCal/src/images/calendar.gif" id="startTime_selector_trigger_${queryCol.paramCode}" style="border-width:0px;cursor:pointer;"/> -
                                <input name="${_endDate}" type="text" class="qdBox" style="width:100px" id="endDate_${queryCol.paramCode}" readonly="readonly"  value="${(paramValueMap[("${_endDate}")])!}"/>
                                <img alt="" align="absmiddle" columnkey="endDate_${queryCol.paramCode}" src="/static/common/JSCal/src/images/calendar.gif" id="endTime_selector_trigger_${queryCol.paramCode}" style="border-width:0px;cursor:pointer;"/>
                            </li>
                        <!-- 下拉框-->
                        <#elseif queryCol.type == "3">
                            <li >${queryCol.paramName}：
                                <select name="${queryCol.paramCode}"  class="listBox">
                                    <option value="">请选择 </option>
                                    <#if dictTypeListMap?? && dictTypeListMap[("${queryCol.paramEnum}")]?? && (dictTypeListMap[("${queryCol.paramEnum}")]?size > 0)>
                                        <#list dictTypeListMap[("${queryCol.paramEnum}")] as data>
                                            <option value="${data.dictCode!}" ${(paramValueMap[("${queryCol.paramCode}")]! == "${data.dictCode!}")?then("selected", "")} >
                                            ${data.dictValue!}
                                            </option>
                                        </#list>
                                    </#if>
                                </select>
                            </li>
                        <!-- 日期 -->
                        <#elseif queryCol.type == "2">
                            <li >${queryCol.paramName}：
                                <input name="${queryCol.paramCode}" type="text" class="qdBox"  style="width:100px" id="startDate_${queryCol.paramCode}"  value="${(paramValueMap[("${queryCol.paramCode}")])!}"/>
                                <img alt="" align="absmiddle" columnkey="startDate_${queryCol.paramCode}" src="/static/common/JSCal/src/images/calendar.gif" id="startTime_selector_trigger_${queryCol.paramCode}" style="border-width:0px;cursor:pointer;"/>
                            </li>
                        <!-- 整型 -->
                        <#elseif queryCol.type == "1">
                             <li >
                                 ${queryCol.paramName}： <input type="text" numberFlag="1" name="${queryCol.paramCode}" class="qdBox" value="${(paramValueMap[("${queryCol.paramCode}")])!}"/>
                             </li>
                        <#else>
                            <li >
                                ${queryCol.paramName}： <input type="text" name="${queryCol.paramCode}" class="qdBox" value="${(paramValueMap[("${queryCol.paramCode}")])!}"/>
                            </li>
                        </#if>
                    </#list>
                </#if>
                <li>
                    <input name="btnQuery" type="button" class="quireBtn" id="btnQuery" value="查询"/>
                    <input name="btnReset" type="button" class="quireBtn" id="btnReset" value="重置"/>

                    <#if rReport?? && rReport.exportFlag?? && rReport.exportFlag == 1>
                        <input name="btnReset" type="button" class="quireBtn" id="btnExport" value="导出"/>
                    </#if>
                </li>
            </ul>

            <table class="table table-bordered table-hover table-striped" style="margin-top: 5px;" id="data_table">
                <tr>
                    <#list showColumnList as showColumn>
                        <th align="left" valign="middle">${showColumn.name}</th>
                    </#list>
                </tr>
                <#if dataList?? && (dataList?size > 0) >
                    <#list dataList as data>
                        <tr>
                            <#list data as showColumn >
                                <td align="left" valign="top" tip="sfd " class="wrap" title="${showColumn.value!}">
                                    <!-- 图片展示-->
                                    <#if showColumn?? && showColumn.type?? && showColumn.type == '3'>
                                        <#if showColumn.value??>
                                            <img src="${showColumn.value!}" width="200px">
                                        </#if>
                                    <#else >
                                        <#if showColumn.showEnum?? && dictTypeMap?? && dictTypeMap[("${showColumn.showEnum!}")]?? >
                                            ${(dictTypeMap[("${showColumn.showEnum!}")])[("${showColumn.value!}")]}
                                        <#else>
                                            ${showColumn.value!}
                                        </#if>
                                    </#if>
                                </td>
                            </#list>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td height="100" colspan="${(showColumnList?size)}" align="center" valign="middle"
                            class="borderN">
                            <div class="no-data">暂无查询记录！</div>
                        </td>
                    </tr>
                </#if>
            </table>
            <input type="hidden" name="v" value="1">
            <#-- 引入 pagebar 的 html 片段-->
            <@pagebar.html query=query />
            </form>

            <form action="/house/chart/${visitKey}.htm" id="chartForm" method="post">
            </form>
        </div>
    </div>
</#assign>
<#include "../../_common/jscal.ftl">
<#-- layout 宏里的 pageScript -->
<#assign pageScript in layout>

<#-- 引入 pagebar 的 js 片段-->
    <@pagebar.script />
    <script type="application/javascript" src="/static/js/j_validate.js" ></script>
    <script type="application/javascript">
        $(document).ready(function () {
            <#if errorMsg?? >
                alert("${errorMsg}")
            </#if>
            $(":input[numberFlag]").each(function(){
                numberInputObj($(this));
            });

            // 初始化时间控件
            $(this).find("img").each(function () {
                var key = $(this).attr("columnkey");
                // console.log(key);
                bindCalendar(key, $(this).attr("id"));
            });

            // 新增按钮
            $("#btnAdd").click(function () {
                window.location.href = "/admin/goods/editInit.htm";
                $("#chartForm").submit();
            });

            // 查询
            $("#btnQuery").click(function () {
                _changePage(1);
            });

            //导出按钮
            $("#btnExport").click(function(){
                <#if dataList?? && (dataList?size > 0) >
                    if(!confirm("最多只能导出20000条数据，是否导出？")){
                      return;
                    }
                    $("#pageForm").attr("action", "/report/${visitKey!}/exportData.htm");
                    $("#pageForm").submit();
                    $("#pageForm").attr("action", "/report/${visitKey}.htm");
                <#else>
                    alert("请先查询出数据");
                </#if>
            });

            // 重置
            $("#btnReset").click(function () {
                $(':input','#pageForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
            });

            $("#categoryId").change(function () {
                var categoryId = $(this).val();
                jQuery.ajax({
                    type: "post",
                    url: "/admin/goods/queryRcBrandByCategoryId.htm",
                    data: "categoryId=" + categoryId,
                    success: function (data) {
                        $("#brandId").empty();
                        $("#brandId").append("<option value=\"\" >商品品牌选择</option>");
                        if (data.result == "1") {
                            if (data.data != "") {
                                data.data.forEach(function (e) {
                                    var optionHtml = "<option value=\"" + e.id + "\">" + e.name + "</option>"
                                    $("#brandId").append(optionHtml);
                                });
                            }
                        }
                    },
                    dataType: "json"
                });
            });

        });
    </script>
</#assign>

<#compress >
<#-- 使用 layout 中定义的 html 宏 -->
    <@layout.html/>
</#compress>



