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
                <a class="nav-link" href="/teacher/home">主页</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/teacher/student">学生管理</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <#if teacher??>
                        ${teacher.name!}
                    <#else >
                        未登陆
                    </#if>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/teacher/password">修改密码</a>
                    <a class="dropdown-item" href="/teacher/profile">个人资料</a>
                    <a class="dropdown-item" href="/teacher/doLogout">退出登陆</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row" style="margin-top: 15px">
        <h2 class="col-6">学生列表</h2>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>专业</th>
                    <th>学院</th>
                    <th>心理健康档案</th>
                </tr>
                <tbody>
                <#list students as student>
                    <tr>
                        <td>${student_index+1}</td>
                        <td>${student.id!}</td>
                        <td>${student.name!}</td>
                        <td>${student.sex!}</td>
                        <td>${student.speciality!}</td>
                        <td>${student.department!}</td>
                        <td>
                            <a class="btn btn-success btn-sm" href="/teacher/document?id=${student.id!}">查看/修改</a>
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