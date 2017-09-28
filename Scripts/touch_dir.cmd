@echo off
for /d %%i in (*) do (
	cd "%%i"
	type nul > tmp.txt
	del tmp.txt
	cd ..
)
