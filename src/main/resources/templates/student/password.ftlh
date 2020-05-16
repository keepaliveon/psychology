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
<div class="container">
    <div class="row" style="margin-top: 15px">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h4>修改密码</h4>
            <form action="/student/doPassword" method="post">
                <div class="form-group">
                    <label for="password">旧密码:</label>
                    <input type="password" class="form-control" name="oldPassword" placeholder="请输入旧密码" required>
                </div>
                <div class="form-group">
                    <label for="password">新密码:</label>
                    <input type="password" class="form-control" name="password" placeholder="请输入新密码" required>
                </div>
                <div class="form-group">
                    <label for="password">密码:</label>
                    <input type="password" class="form-control" placeholder="请再一次输入密码" required>
                </div>
                <button type="submit" class="btn btn-lg btn-primary">修改密码</button>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>