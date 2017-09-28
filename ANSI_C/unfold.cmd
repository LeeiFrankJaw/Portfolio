@echo off
if not "%cd:~-35%"=="The C Programming Language (ANSI C)" (
    echo We're in the wrong directory.
    goto quit
)

for /D %%i in (*) do (
    for /D %%j in ("%%i\*") do (
        move "%%j\*" "%%i"
        rd "%%j"
    )
)

:quit
pause