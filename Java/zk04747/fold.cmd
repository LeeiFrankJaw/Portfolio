@echo off

if "%cd:~-4%" neq "4747" (
    echo We're in the wrong directory
    goto quit
)

for /D %%i in (*) do (
    pushd %%i
    for %%j in ("Ex*" "P*") do (
        if not exist "%%~nj" mkdir "%%~nj"
        move "%%j" "%%~nj"
        REM if "%%~xj"==".java" javac "%%~nj\%%j"
    )
    REM if exist *.class del *.class
    popd
)

move "Chapter 3\Complex.class" "Chapter 3\P13"
move "Chapter 3\RationalNum.class" "Chapter 3\P14"

move "Chapter 5\MyWindowDemo.class" "Chapter 5\Ex5_2"
move "Chapter 5\ButtonDemo.class" "Chapter 5\Ex5_3"
move "Chapter 5\MyPanelEx5_4.class" "Chapter 5\Ex5_4"
move "Chapter 5\MyWindow.class" "Chapter 5\Ex5_6"
move "Chapter 5\ScrollPane.class" "Chapter 5\Ex5_6"
move "Chapter 5\MyPanelEx5_7.class" "Chapter 5\Ex5_7"
move "Chapter 5\Sqr.class" "Chapter 5\Ex5_8"
move "Chapter 5\JPanel_1.class" "Chapter 5\Ex5_10"
move "Chapter 5\JPanel_2.class" "Chapter 5\Ex5_10"

move "Chapter 6\MyFrameEx6_1.class" "Chapter 6\Ex6_1"
move "Chapter 6\MyFrameEx6_2.class" "Chapter 6\Ex6_2"
move "Chapter 6\JPanel_1.class" "Chapter 6\Ex6_2"
move "Chapter 6\JPanel_2.class" "Chapter 6\Ex6_2"
move "Chapter 6\MyFrameEx6_3.class" "Chapter 6\Ex6_3"
move "Chapter 6\ComboBoxDemo.class" "Chapter 6\Ex6_4"
move "Chapter 6\LaunchFrame.class" "Chapter 6\Ex6_5"
move "Chapter 6\MenuWindow.class" "Chapter 6\Ex6_5"
move "Chapter 6\quit.class" "Chapter 6\Ex6_5"
move "Chapter 6\MyDialog.class" "Chapter 6\Ex6_6"
move "Chapter 6\MyWindow.class" "Chapter 6\Ex6_6"
move "Chapter 6\MyScrollBar.class" "Chapter 6\Ex6_7"
move "Chapter 6\MyFrameEx6_7.class" "Chapter 6\Ex6_7"
move "Chapter 6\MyFrameEx6_8.class" "Chapter 6\Ex6_8"
move "Chapter 6\MyPanel.class" "Chapter 6\Ex6_8"
move "Chapter 6\MyListenerEx6_9.class" "Chapter 6\Ex6_9"
move "Chapter 6\MyWindowEx6_9.class" "Chapter 6\Ex6_9"

:quit
pause