@echo off
setlocal EnableDelayedExpansion
if not exist %1 mkdir %1
for /f "delims=" %%i in ('2^>nul dir /a-d /b %2') do (
	set file=%%i
	set file=!file:%%=%%%%!
	if not "%%i"==".gitignore" call make_link "%~2\!file!" "%~1\!file!"
)

for /f "delims=" %%i in ('2^>nul dir /ad /b %2') do (
	if not "%%i"==".git" call linkdir "%~1\%%i" "%~2\%%i"
)
