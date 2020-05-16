<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <title>心理健康管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <style>
        th, td {
            white-space: nowrap;
            text-align: center;
        }

        .avatar {
            width: 25px;
            height: 25px;
        }
    </style>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar bg-dark navbar-expand-md navbar-dark">
    <a class="navbar-brand" href="/student/home">心理健康管理系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/student/home">个人资料</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/student/consult">心理咨询</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <#if student??>
                        ${student.name!}
                    <#else >
                        未登陆
                    </#if>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/student/password">修改密码</a>
                    <a class="dropdown-item" href="/student/doLogout">退出登陆</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row" style="margin-top: 15px">
        <h2 class="col-6">咨询师列表</h2>
        <#--        <div class="col-6">-->
        <#--            <a href="/textbook_add" class="btn btn-primary float-right">录入新教材信息</a>-->
        <#--        </div>-->
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>操作</th>
                </tr>
                <tbody>
                <#list teachers as teacher>
                    <tr>
                        <td>${teacher_index+1}</td>
                        <td>${teacher.id!}</td>
                        <td>${teacher.name!}</td>
                        <td>${teacher.sex!}</td>
                        <td>
                            <#if teacher.subscribed>
                                <a class="btn btn-default btn-sm" href="#">已预约</a>
                            <#else >
                                <a class="btn btn-success btn-sm" href="/student/doSubscribe?id=${teacher.id!}">预约</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>