#!/bin/bash

# 80번 포트를 사용 중인 프로세스를 찾아서 종료
echo "Checking for processes on port 80..."
PID=$(lsof -t -i :80)

if [ -n "$PID" ]; then
    echo "Process found on port 80. Stopping process with PID $PID..."
    sudo kill -9 $PID
else
    echo "No process found on port 80."
fi

