<#-- 宏：html -->
<#-- title：页面标题（浏览器标题） -->
<#-- pageHead：html附加head内容，一般用于设置额外的css代码 -->
<#-- pageHeader：页面标题（在页面上显示的标题） -->
<#-- pageBody：页面内容 -->
<#-- pageScript：页面附加脚本，一般用于设置额外的js代码 -->
<#macro html >
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${title!"admin"}</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/plugin/font-awesome/css/font-awesome.min.css">
    <!-- AdminLTE 样式文件 -->
    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">
    <!-- AdminLTE 主题样式文件 -->
    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/skin-purple.min.css">
    ${pageHead!}
</head>
<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <header class="main-header">

        <a href="/index.htm" class="logo">
            <span class="logo-mini">数橙科技</span>
            <span class="logo-lg">数橙-极速报表</span>
        </a>

        <nav class="navbar navbar-static-top">
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">切换导航</span>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="javascript:void(0);" id="refreshAuthorizationBtn">
                            <i class="fa fa-refresh"></i>
                            <span class="hidden-xs">刷新权限</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);">
                            <i class="fa fa-user"></i>
                            <span class="hidden-xs">
                                请登录
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="#" data-toggle="modal" data-target="#logoutConfirmModal">
                            <i class="fa fa-power-off"></i>
                            <span class="hidden-xs">退出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar">
        <section class="sidebar">
            <ul class="sidebar-menu" data-widget="tree">
                <li>
                    <a href="/index.htm"><i class="fa fa-dashboard"></i> <span> 首页</span></a>
                </li>
                <li>
                    <a href="/admin/report/reportList.htm"><i class="fa fa-dashboard"></i> <span> 报表管理</span></a>
                </li>
                <!--
                     <li>
                        <a href="/admin/quartz/sqllist.htm"><i class="fa fa-dashboard"></i> <span> 任务管理</span></a>
                    </li>

                <li class="treeview">
                    <a href="javascript:void(0);"><i class="fa fa-circle"></i> <span> 菜单测试</span>
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/index.htm"><i class="fa fa-circle"></i> 任务列表</a></li>
                        <li><a href="/index.htm"><i class="fa fa-circle"></i> 添加任务</a></li>
                    </ul>
                </li>
                 -->
            </ul>
        </section>
    </aside>

    <section class="content-wrapper">
        <section class="content-header">
            <div>
                <ol class="breadcrumb" style="margin-bottom: 0px;">
                    <li><a href="/index.htm"><i class="fa fa-dashboard"></i> <span> 首页</span></a></li>
                    <li class="active">${title!"admin"}</li>
                </ol>
            </div>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <#if pageBody??>
                        ${pageBody}
                    <#else>
                        <div class="alert alert-warning">这是一个空页面</div>
                    </#if>
                </div>
            </div>
        </section>
    </section>

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>版本</b> 1.0.0
        </div>
        <strong>版权所有 &copy; 2019 <a href="javascript:void(0);">数橙科技</a>.</strong>
    </footer>

</div>
<div id="logoutConfirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm" role="document" style="margin-top: 100px;">
        <div class="modal-content">
            <div class="modal-body" align="center">
                <h4>您是否要退出?</h4>
                <div class="row">
                    <div class="col-md-12">
                        <button id="logoutConfirmBtn" class="btn btn-warning">退出</button>
                        <button class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/static/plugin/jquery-1.12.4.min.js"></script>
<script src="/static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="/static/adminlte/dist/js/adminlte.min.js"></script>
<script src="/static/plugin/jquery.form.js"></script>
<script src="/static/plugin/jquery.blockUI.js"></script>
<script type="text/javascript">
    $(document).ajaxComplete(function (event, xhr, options) {
        // 重定向处理
        if (xhr.status == 302) {
            var redirectUrl = xhr.getResponseHeader("Location");
            if (redirectUrl != null) {
                window.location = redirectUrl
            }
        } else if (xhr.status == 200) {
            var responseJson = JSON.parse(xhr.responseText);
            // 未登录错误码和重复登录，统一处理
            if (responseJson.code == 401) {
                var loginUrl = xhr.getResponseHeader("loginUrl");
                if (loginUrl != null) {
                    window.location = loginUrl
                }
            }
        }
    });
    $("#refreshAuthorizationBtn").on("click", function () {
        $.blockUI({ message: '正在刷新权限，请稍等...' });
        $.get("/ajax/common/authorization/refresh.htm", function (res) {
            if (!res.success) {
                alert(res.message);
            }
            $.unblockUI();
        }, "json");
    });

    $('#logoutConfirmBtn').click(function () {
        window.location.href = "/signout.htm";
    });
    $currMenuItem = $('.sidebar-menu').find("a[href='" + location.pathname + "']");
    if ($currMenuItem.parent().parent().hasClass("treeview-menu")) {
        $currMenuItem.parent().parent().show();
        $currMenuItem.parent().parent().parent().addClass("menu-open active");
    }
    $currMenuItem.parent().addClass("active");

</script>
    ${pageScript!}
</html>
</#macro>