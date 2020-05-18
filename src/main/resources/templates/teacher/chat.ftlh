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
    <h4>教师端(${chatSession.name!})</h4>
    <input type="text" id="message_input">
    <button onclick="sendMessage()">发送</button>
</div>
<script type="text/javascript">
    let webSocket = new WebSocket("ws://localhost:8080/websocket/${chatSession.fromId!}");
    webSocket.onopen = function () {
        console.log("教师端(${chatSession.name!})与websocket_server的连接打开了")
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