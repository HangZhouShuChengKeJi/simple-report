<#--
宏： html
参数： query
描述： 分页 html 片段
-->
<#macro html query>
    <#if query.pageCount?? && (query.pageCount > 1)>
        <div class="pagelist">
            <form id="pageForm">
                <a href="javascript:void(0);" pageNo="${query.prePage!}">&lt; 上一页</a>
                <#if (query.startPage > 1)>
                    <span>...</span>
                </#if>
                <#if (query.pageCount > 1)>
                    <#list query.startPage .. query.endPage as curPageNo>
                        <#if curPageNo == query.pageNo>
                            <span class="current">${curPageNo}</span>
                        <#else>
                            <a href="javascript:void(0);" pageNo="${curPageNo}">${curPageNo}</a>
                        </#if>
                    </#list>
                </#if>
                <#if (query.endPage < query.pageCount)>
                    <span>...</span>
                </#if>
                <a href="javascript:void(0);" pageNo="${query.nextPage!}">下一页 &gt;</a>&nbsp;共${query.pageCount}页 转到第
                <input name="pageNo" type="text" class="pageBox" id="turnPageNo"/>
                <input name="btnTurnPage" type="submit" class="quireBtn" id="btnTurnPage" value="确定"/>
            </form>
        </div>
    </#if>
</#macro>

<#--
宏：script
描述： 分页 js 脚本
-->
<#macro script>
    <script type="application/javascript">

        $(document).ready(function () {
            // 点击页码链接
            $(".pagelist a").bind("click", function () {
                var no = $(this).attr("pageNo");
                if (no == undefined) {
                    return false;
                }
                _changePage(no);
                return false;
            })

            // 页码输入框如果输入非数字，则直接过滤
            $(".pagelist input[name=turnPageNo]").bind("keyup", function () {
                var inputNo = $(this).val();
                $(this).val(inputNo.replace(/\D/g, ''));
            })

            // 点击确定按钮
            $("#btnTurnPage").bind("click", function () {
                var reg = /^[1-9]\d{0,13}$/;
                var pageNo = $(".pagelist input[name=turnPageNo]").val();

                if (reg.test(pageNo)) {
                    _changePage(pageNo);
                } else {
                    $(".pagelist input[name=turnPageNo]").focus();
                }
            })
        });

        /**
         * 页面跳转
         */
        function _changePage(pageNo) {
            var pageForm = $("#pageForm");
            if (pageForm.length == 0) {
                pageForm = $("form").eq(0);
            }
            pageForm.find("input[name='pageNo']").val(pageNo);
            pageForm.submit();
        }
    </script>
</#macro>
