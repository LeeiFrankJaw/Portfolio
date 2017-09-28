@echo off
set gs_path="D:\Program Files\gs\gs9.19\bin\gswin64c.exe"
doskey /listsize=300
doskey ls=ls --color=auto $*
doskey gs=%gs_path% -q $*
doskey pdf2jpg=%gs_path% -sDEVICE=jpeg -dTextAlphaBits=4 -dGraphicsAlphaBits=4 -r150 $*
doskey pdf2png=%gs_path% -dSAFER -sDEVICE=png16m -dTextAlphaBits=4 -dGraphicsAlphaBits=4 -r300 $*
doskey pdf2gray=%gs_path% -sDEVICE=pdfwrite -dProcessColorModel=/DeviceGray -dColorConversionStrategy=/Gray $*
doskey define=curl dict://dict.org/d:"$*"

REM set INFOPATH=d:/emacs-24.4/share/info/;d:/mingw/share/info;d:/mingw/msys/1.0/share/info;:/mingw/share/info:/usr/share/info:/d/emacs-24.4/share/info
doskey ll=ls --color=auto -ahl $*
doskey which=sh -c "which $*"

REM set cbok=D:\Archive\EReading\CS\Book
REM set mbok=D:\Archive\EReading\Math\Book
REM set anal=D:\Files\Books\Analysis
REM set alge=D:\Files\Books\Algebra
REM set eecs=D:\Files\Books\EECS
REM set ltex=D:\Files\Books\LaTeX
REM set tex=D:\Files\TeX

set http_proxy=127.0.0.1:1080
set https_proxy=127.0.0.1:1080
set rsync_proxy=127.0.0.1:1080
