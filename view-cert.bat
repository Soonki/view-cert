@echo off
setlocal enabledelayedexpansion

set file=%1
set file=!file:"=!
set password=%2
set ext=%~x1
set ext=!ext:.=!
REM echo ext=!ext!
call view-!ext!.bat "!file!" %2 > "!file!.info.txt.tmp" && (
	move /y "!file!.info.txt.tmp" "!file!.info.txt" > NUL 2>&1
	echo Successfully created "!file!.info.txt"
	perl -n %~dp0view-cert-refine.pl < "!file!.info.txt"
	call e "!file!.info.txt"
) || (
	echo Error in "!file!.info.txt"
	type "!file!.info.txt.tmp"
)
