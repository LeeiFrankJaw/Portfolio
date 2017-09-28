@echo off
if not "%cd:~-35%"=="The C Programming Language (ANSI C)" (
    echo We're in the wrong directory.
    goto quit
)

for /D %%i in (*) do (
    pushd "%%i"
    for %%j in ("*.c" "*.exe" "*.obj") do (
        if not exist "%%~nj" mkdir "%%~nj"
        move "%%~j" "%%~nj"
    )
    if exist "*~" del "*~"
    if exist "*.txt" (
        if not exist "text" mkdir "text"
        move "*.txt" "text"
    )
    popd
)

:quit
pause