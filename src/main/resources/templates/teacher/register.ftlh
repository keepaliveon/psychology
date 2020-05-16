<!DOCTYPE html>
<html>
<head>
    <title>注册页面</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form action="/teacher/doRegister" method="post">
                <div class="form-group">
                    <label for="email">工号:</label>
                    <input type="text" class="form-control" name="id" placeholder="请输入工号" required>
                </div>
                <div class="form-group">
                    <label for="email">姓名:</label>
                    <input type="text" class="form-control" name="name" placeholder="请输入姓名" required>
                </div>
                <div class="form-group">
                    <label for="email">性别:</label>
                    <input type="text" class="form-control" name="sex" placeholder="请输入性别" required>
                </div>
                <div class="form-group">
                    <label for="password">密码:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required>
                </div>
                <div class="form-group">
                    <label for="password">密码:</label>
                    <input type="password" class="form-control" id="re_password" placeholder="请再一次输入密码" required>
                </div>
                <button type="submit" class="btn btn-lg btn-primary">注册</button>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
<script src="/static/js/jquery.js"></script>
<script src="/static/js/bootstrap.js"></script>
</html>