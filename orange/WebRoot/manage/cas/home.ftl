<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="首页">
<style>
    .font-focus{
        font-weight: 700;font-size: 16px;color: #f50 !important;text-decoration: underline;
    }
</style>

<script>
    $(function() {
        $( "#tabs" ).tabs({
        });
    });
</script>
        <div id="tabs">
            <ul>
                <li><a href="#tabs-1" style="font-size: 14px;">统计报表</a>
                    
                </li>
            </ul>
            <div id="tabs-1">
                <div class="alert alert-success" style="margin-bottom: 2px;text-align: left;">
                    <span class="badge badge-important">用户</span>&nbsp;<strong>用户汇总如下：</strong>
                </div>
                <table class="table table-bordered">
                    <tr>
                        <td>新注册用户数：<a class="font-focus" href="${basepath}/cas/member/selectList?init=y">${newUserNum}</a></td>
                        <td>总用户数：<a class="font-focus" style="color: #f50;" href="${basepath}/cas/member/selectList?init=y">${totalUserNum}</a></td>
                    </tr>
                </table>

                
            </div>
            
        </div>
        <!-- tab end -->
</@page.pageBase>