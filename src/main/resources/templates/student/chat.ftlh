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
    <h2 style="margin-top: 10px">学生端(${chatSession.fromName!})</h2>
    <div class="card">
        <div class="card-header"></div>
        <div class="card-body"></div>
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
    let webSocket = new WebSocket("ws://localhost:8080/websocket/${chatSession.fromId!}:${chatSession.subscribeId!}");
    webSocket.onopen = function () {
        console.log("学生端(${chatSession.fromName!})与websocket_server的连接打开了")
    }
    webSocket.onmessage = function (evt) {
        console.log(evt.data)
    }
    let element = document.getElementById("message_input");

    function sendMessage() {
        webSocket.send("${chatSession.toId}:" + element.value)
    }
</script>
</body>
</html>