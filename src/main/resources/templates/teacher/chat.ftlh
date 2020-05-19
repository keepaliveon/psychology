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
<div class="container">
    <h3 style="margin-top: 10px">教师端(${chatSession.fromName!})</h3>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">与${chatSession.toName!}的对话</h5>
            <ul></ul>
        </div>
        <div class="card-footer">
            <div class="input-group">
                <input type="text" class="form-control" id="message_input" placeholder="请输入内容">
                <div class="input-group-append">
                    <button class="btn btn-success" type="submit" onclick="sendMessage()">发送</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    let webSocket = new WebSocket("ws://localhost:8080/websocket/${chatSession.fromId!}:${chatSession.toId!}");
    webSocket.onopen = function () {
        console.log("教师端(${chatSession.fromName!})与websocket_server的连接打开了")
    }
    webSocket.onmessage = function (evt) {
        $("ul").append("<li>" + evt.data + "</li>");
    }

    function sendMessage() {
        let data = {
            fromName: '${chatSession.fromName!}',
            toName: '${chatSession.toName!}',
            fromId: '${chatSession.fromId!}',
            toId: '${chatSession.toId!}',
            text: $("#message_input").val(),
        }
        webSocket.send(JSON.stringify(data));
        $("#message_input").val("")
    }
</script>
</body>
</html>