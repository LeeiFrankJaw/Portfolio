@echo off
findstr /i /r "SCRNSAVE\.EXE=.*scr" %1 > nul
if not errorlevel 1 (
	sed "s/SCRNSAVE\.EXE=.*scr/SCRNSAVE\.EXE=/gi" %1 > tmp.txt
	move /y tmp.txt %1 > nul
)
