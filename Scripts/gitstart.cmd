@echo off
for /f "delims=" %%i in ('git remote get-url origin') do start %%i
