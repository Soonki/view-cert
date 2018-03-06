@echo off
if .%1. == .. (
	echo Usage: %~nx0 filename.pfx [ password [ alias ] ]
	echo        %~nx0 filename.p12 [ password [ alias ] ]
	goto exit
)

if not .%3. == .. (
	echo ^>^> keytool -v -list -keystore %1 -storetype PKCS12 -storepass %2 -alias %3
	keytool -v -list -keystore %1 -storetype PKCS12 -storepass %2 -alias %3
) else (
	if not .%2. == .. (
		echo ^>^> keytool -v -list -keystore %1 -storetype PKCS12 -storepass %2
		keytool -v -list -keystore %1 -storetype PKCS12 -storepass %2
	) else (
		echo ^>^> keytool -v -list -keystore %1 -storetype PKCS12 -storepass changeit
		keytool -v -list -keystore %1 -storetype PKCS12 -storepass changeit
	)
)

:exit
