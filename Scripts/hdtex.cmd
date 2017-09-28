@echo off
REM if not exist TeXSrc (
    REM mkdir TeXSrc
    REM attrib +H TeXSrc
REM )

pushd "%~dp0"
for /f "delims=" %%i in (hdtex.txt) do set pattern=%%i
popd

for %%i in (%pattern%) do attrib +H "%%i"
