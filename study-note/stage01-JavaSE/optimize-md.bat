@echo off
if not defined TAG (
    set TAG=1
    start wt -p "cmd" %0
    exit
)

java -jar optimize-md.jar

::start wt
pause

