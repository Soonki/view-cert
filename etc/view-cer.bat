@echo off
if .%1. == .. (
	echo usage: %~n0 filename.cer
	goto exit
)
echo ^>^> keytool -printcert -file %1
echo.
keytool -printcert -file %1
:exit