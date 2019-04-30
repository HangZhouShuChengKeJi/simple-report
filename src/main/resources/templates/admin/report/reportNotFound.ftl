<#-- 必须引入 layout 宏定义文件 -->
<#import "../../_common/layout_admin.ftl" as layout >
<#-- 引入分页组件 -->
<#import "../../_common/pagebar.ftl" as pagebar >

<#-- layout 宏里的 title -->
<#assign title="报表不存在" in layout />

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
            报表不存在
        </div>
    </div>
</#assign>

<#compress >
<#-- 使用 layout 中定义的 html 宏 -->
    <@layout.html/>
</#compress>



