@echo off
set fnf=File Not Found
for /f "delims=" %%i in (%~1) do if not "%%i"=="%fnf%" (
	echo/
	echo %hr%
	set relpath=%%~dpi
	set relpath=!relpath:%cd%\=!
	if "!relpath!"=="" set relpath=.
	set file=%%~nxi
	set file=!file:%%=%%%%!
	set rpnx=!relpath!\!file!
	call make_link "!rpnx!" "%~2\!rpnx!"
	echo %hr%
	echo/
)
