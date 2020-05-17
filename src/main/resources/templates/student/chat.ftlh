<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>咨询页面</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
</head>
<script src="/static/js/jquery.js"></script>
<script src="/static/js/bootstrap.js"></script>

<body>
<input type="text" id="message_input">
<button onclick="sendMessage()">发送</button>


<script type="text/javascript">
    let webSocket = new WebSocket("ws://localhost:8080/websocket/${chatSession.fromId!}");
    webSocket.onopen = function () {
        console.log("连接启动了")
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