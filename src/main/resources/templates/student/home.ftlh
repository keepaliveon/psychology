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
                <a class="nav-link active" href="/student/home">个人资料</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/student/consult">心理咨询</a>
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
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h4>个人信息</h4>
            <#if info?exists>
                <#if info.type="success">
                    <div class="alert alert-success alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>提示!</strong> ${info.message}
                    </div>
                <#elseif info.type="error">
                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>错误!</strong> ${info.message}
                    </div>
                </#if>
            </#if>
            <form action="/student/doUpdate" method="post">
                <div class="form-group">
                    <label for="email">学号:</label>
                    <input type="text" class="form-control" name="id" placeholder="请输入学号" value="${student.id!}" required readonly>
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
                    <label for="email">专业:</label>
                    <input type="text" class="form-control" name="speciality" placeholder="请输入专业" value="${student.speciality!}" required>
                </div>
                <div class="form-group">
                    <label for="email">学院:</label>
                    <input type="text" class="form-control" name="department" placeholder="请输入学院" value="${student.department!}" required>
                    <input type="password" name="password" value="${student.password!}" hidden>
                </div>
                <button type="submit" class="btn btn-lg btn-primary">保存修改</button>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>