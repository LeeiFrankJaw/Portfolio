@echo off
setlocal
if "%*"=="" (
    set _pttrn=.*
) else (
    set _pttrn=%*
)
for /f "delims=" %%i in ('dir /a /b %_pttrn%') do attrib -H "%%i"
