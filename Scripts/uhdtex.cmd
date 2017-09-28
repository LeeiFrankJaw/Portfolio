@echo off
REM if not exist TeXSrc (
	REM echo Error, there's no TeXSrc folder.
REM ) else (
	REM move TeXSrc\* .
	REM rmdir TeXSrc
REM )

pushd "%~dp0"
for /f "delims=" %%i in (hdtex.txt) do set pattern=%%i
popd

for /f "delims=" %%i in ('dir /a /b %pattern%') do attrib -H "%%i"
