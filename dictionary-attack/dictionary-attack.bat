@echo off
@if exist %~dp0dictionary-attack-jar-with-dependencies.jar (
    java -cp %~dp0dictionary-attack-jar-with-dependencies.jar com.demo.App %*
) else (
    java -cp %~dp0target\dictionary-attack-jar-with-dependencies.jar com.demo.App %*
)
