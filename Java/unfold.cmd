@echo off

if "%cd:~-4%" neq "4747" (
    echo We're in the wrong directory
    goto quit
)

for /D %%i in (*) do (
    for /D %%j in ("%%i\*") do (
        for %%k in ("%%j\*") do move "%%k" "%%i"
        rmdir "%%j"
    )
)

:quit
pause