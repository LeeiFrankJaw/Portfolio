@echo off
setlocal EnableDelayedExpansion
echo -sDEVICE=pdfwrite -dPDFSETTINGS=/prepress -q > "%temp%\_.at"
set params=%*

:parse
echo !params! | findstr /b /c:- >nul 2>&1
if not errorlevel 1 (
	for /f "tokens=1*" %%i in ("!params!") do (
		echo %%i >> "%temp%\_.at"
		set params=%%j
	)
	goto :parse
)

call :proc !params!
goto :eof

:proc
if "%~1"=="" goto usage
if "%~2"=="" (
	set _outf="%~n1.pdf"
) else (
	set _outf=%2
)
if %~x1==.eps echo -dEPSCrop >> "%temp%\_.at"
echo -o %_outf% %1 >> "%temp%\_.at"
%gs_path%  @"%temp%\_.at"
goto clean

:usage
echo Usage: ps2pdf [options...] input.[e]ps [output.pdf]

:clean
del "%temp%\_.at"
goto :eof
