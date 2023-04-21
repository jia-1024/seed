#!/bin/bash

# 程序端口
app_port=5000
# 程序文件入口
app_path="java.jar"
# 执行程序的虚拟机/编译器/解释器...
cmd_bin="java"
# 组织虚拟机/编译器/解释器 运行程序的命令
cmd_bin_exec=""

# 组织最终运行程序的命令
end_exec_cmd() {
  nohup $cmd_bin_exec >log.log 2>&1 &
}

run_app() {
  pid=$(ss -ntlp | grep $app_port | awk '{print $6}' | awk -F'=' '{print $2}' | awk -F',' '{print $1}')
  if [ -z "$pid" ]; then
    # 真正执行的地方
    end_exec_cmd
    echo "启动成功"
  else
    echo "程序已经运行，pid=$pid)"
  fi
}

stop_app() {
  pid=$(ss -ntlp | grep $app_port | awk '{print $6}' | awk -F'=' '{print $2}' | awk -F',' '{print $1}')
  if [ -z "$pid" ]; then
    echo "停止成功"
  else
    kill -15 "$pid"
    echo "pid=$pid,停止成功"
  fi
}

restart_app() {
  stop_app
  run_app
}

status_app() {
  pid=$(ss -ntlp | grep $app_port | awk '{print $6}' | awk -F'=' '{print $2}' | awk -F',' '{print $1}')
  if [ -z "$pid" ]; then
    echo "程序未运行"
  else
    echo "app is running,pid=$pid"
  fi
}

case $1 in
"run")
  run_app
  ;;
"stop")
  stop_app
  ;;
"restart")
  restart_app
  ;;
"status")
  status_app
  ;;
*)
  echo 'You must select one in [ run | stop | restart | status ]'
  ;;
esac
