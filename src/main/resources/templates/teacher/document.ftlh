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
                <a class="nav-link" href="/teacher/student">学生管理</a>
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
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h4>学生档案</h4>
            <form action="/teacher/doDocument" method="post">
                <div class="form-group">
                    <label for="email">学号:</label>
                    <input type="text" class="form-control" name="id" placeholder="请输入工号" value="${student.id!}" required readonly>
                </div>
                <div class="form-group">
                    <label for="email">姓名:</label>
                    <input type="text" class="form-control" name="name" placeholder="请输入姓名" value="${student.name!}" required>
                </div>
                <div class="form-group">
                    <label for="email">性别:</label>
                    <input type="text" class="form-control" name="sex" placeholder="请输入性别" value="${student.sex!}" required>
                </div>
                <div class="form-group">
                    <label for="email">档案:</label>
                    <textarea class="form-control" rows="3" name="document">${student.document!}</textarea>
                </div>
                <input type="password" name="department" value="${student.department!}" hidden>
                <input type="password" name="speciality" value="${student.speciality!}" hidden>
                <input type="password" name="password" value="${student.password!}" hidden>
                <button type="submit" class="btn btn-lg btn-primary">保存修改</button>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>