
- 下载[Git](https://mirrors.huaweicloud.com/git-for-windows/v2.26.0.windows.1/Git-2.26.0-64-bit.exe)，安装完成后执行```git --version```检查安装是否正确

- 下载[Maven](https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)，解压并将其bin目录配置到系统path环境变量下，执行```mvn --version```检查安装是否正确

- 执行```git clone https://gitee.com/kevin586/psychology.git```将代码拉取到本地，并执行```cd psychology```切换到项目根目录

- 在项目根目录下执行```mvn spring-boot:run```

- 打开浏览器访问学生登陆入口localhost:8080/student/login，访问教师登陆入口localhost:8080/teacher/login