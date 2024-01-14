FROM ubuntu:latest
LABEL authors="sergeymac"

ENTRYPOINT ["top", "-b"]