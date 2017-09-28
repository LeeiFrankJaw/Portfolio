@echo off
for /f "delims=" %%i in ('dir /a /b *') do @lslink "%%i" | find /v /c "" | find "1" 1>nul && echo %%i
