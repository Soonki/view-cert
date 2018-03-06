@echo off
if .%1. == .. (
	echo Usage: %~nx0 filename.pem
	goto exit
)

echo ^>^> openssl x509 -in %1 -inform pem -noout -text
echo.
openssl x509 -in %1 -inform pem -noout -text

:exit
