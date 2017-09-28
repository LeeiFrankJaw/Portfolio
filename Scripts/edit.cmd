@echo off
if [%1]==[] (
    tasklist | find "emacs" >nul
	if errorlevel 1 runemacs
) else (
    emacsclientw -na runemacs %*
)
