@echo off

setlocal EnableDelayedExpansion

set case1=c:/Users/FrankZ/Desktop/Links/CSC 140/1.1/Web Exercises/WebEx2.txt
set case2=c:/Users/FrankZ/Desktop/Links/CSC 140/1.1/Web Exercises/WebEx3.txt
set case3=c:/Users/FrankZ/Desktop/Links/CSC 140/1.2/Exercises/1.2.10.txt

for %%i in ("Histogram*.exe") do (
    for /L %%j in (1,1,3) do (
        %%i < !case%%j! > %%~niOutput%%j.txt
	start notepad %%~niOutput%%j.txt
	if %errorlevel% GTR 0 (
            echo %%i is buggy.
            goto :eof
	)
    )
)
