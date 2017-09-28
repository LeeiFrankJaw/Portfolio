@echo off
for %%i in (*.themepack) do start "" /wait "%%i"
pushd %LocalAppData%\Microsoft\Windows\Themes
forfiles /s /m *.theme /c "cmd /c call rmscrnsave @file"
popd
