@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  swerve-robot startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SWERVE_ROBOT_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\swerve-robot-2021.2.2.jar;%APP_HOME%\lib\robot-2021.2.2.jar;%APP_HOME%\lib\navx-java-3.1.400.jar;%APP_HOME%\lib\wpilibj-java-2021.2.2.jar;%APP_HOME%\lib\wpimath-java-2021.2.2.jar;%APP_HOME%\lib\ntcore-java-2021.2.2.jar;%APP_HOME%\lib\wpiutil-java-2021.2.2.jar;%APP_HOME%\lib\opencv-java-3.4.7-5.jar;%APP_HOME%\lib\cscore-java-2021.2.2.jar;%APP_HOME%\lib\cameraserver-java-2021.2.2.jar;%APP_HOME%\lib\hal-java-2021.2.2.jar;%APP_HOME%\lib\common-2021.2.2.jar;%APP_HOME%\lib\ejml-simple-0.38.jar;%APP_HOME%\lib\jackson-databind-2.10.0.jar;%APP_HOME%\lib\jackson-annotations-2.10.0.jar;%APP_HOME%\lib\jackson-core-2.10.0.jar;%APP_HOME%\lib\api-java-5.17.6.jar;%APP_HOME%\lib\wpiapi-java-5.17.6.jar;%APP_HOME%\lib\SparkMax-java-1.5.1.jar;%APP_HOME%\lib\wpilibNewCommands-java-2021.2.2.jar;%APP_HOME%\lib\wpilibOldCommands-java-2021.2.2.jar;%APP_HOME%\lib\ejml-fdense-0.38.jar;%APP_HOME%\lib\ejml-dsparse-0.38.jar;%APP_HOME%\lib\ejml-ddense-0.38.jar;%APP_HOME%\lib\ejml-cdense-0.38.jar;%APP_HOME%\lib\ejml-zdense-0.38.jar;%APP_HOME%\lib\ejml-core-0.38.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\opencv-java-3.4.7-2.jar;%APP_HOME%\lib\gson-2.8.6.jar

@rem Execute swerve-robot
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SWERVE_ROBOT_OPTS%  -classpath "%CLASSPATH%" frc.swerverobot.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SWERVE_ROBOT_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SWERVE_ROBOT_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
