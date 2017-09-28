@echo off
setlocal

if not %~x1==.px goto usage
if "%2"=="" (
	set _outf=%~n1.pdf
) else (
	set _outf=%2
)

m4 %1 > %~n1.ps
ps2pdf %~n1.ps %_outf%
goto :eof

:usage
echo Usage: px2pdf [options...] input.px [output.pdf]
