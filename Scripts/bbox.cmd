@echo off
setlocal

if %1/==/ goto usage
if %2/==/ (
	set _out=^&1
) else if %2/==-/ (
	set _out=^&1
) else (
	set _out=%2
)

%gs_path% -sDEVICE=bbox -dNOPAUSE -dBATCH -q %1 2>%_out%
goto :eof

:usage
echo Usage: px2pdf [options...] input.{[e]ps^|pdf} [output]
goto :eof
