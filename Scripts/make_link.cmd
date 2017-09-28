@echo off
if not exist "%~dp2" mkdir "%~dp2"
if exist "%~2" (
	for /f "delims=" %%i in ('fsutil hardlink list "%~1"') do (
		if "D:%%i"=="%~f2" goto :eof
	)
	fc "%~1" "%~2" 1>nul
	if errorlevel 1 (
		echo Failure: The files "%~1" and "%~f2" differ.
	) else (
		del /a "%~1" && mklink /h "%~1" "%~2"
		if errorlevel 1 (
			echo Failure: Unknow error in line 11 of %0 while
			echo/    del /a "%~1" ^&^& mklink /h "%~1" "%~2"
		) else (
			echo Success: The files "%~1" and "%~f2" have been hardlinked.
		)
	)
) else (
	mklink /h "%~2" "%~1"
	if errorlevel 1 (
		echo Failure: Unknow error in line 20 of %0 while
		echo/    mklink /h "%~2" "%~1"
	) else (
		echo Success: The files "%~1" and "%~f2" have been hardlinked.
	)
)
