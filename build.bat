@echo off
REM #################################################################################################################################
REM ##   Example use:                                                                                                               #
REM ##   																									                    	#
REM ##   build.bat local app // set config.js to localhost:8080 and put all uncompress static files in public server folder         #
REM ##   build.bat dev app   // set config.js to DEV and put all uncompress static files in public server folder.                   #
REM ##   build.bat local dist // set config.js to localhost:8080 and put dist static files in public server folder.                 #
REM #################################################################################################################################

IF "%VG_HOME%"=="" (
	ECHO The VG_HOME is NOT defined in your local environment.
	EXIT /b
) 

SET static_src=%VG_HOME%\BK\src\main\resources\public

IF ["%1"]==["local"] (
	SET app_env=	
) ELSE (
	SET app_env=:%1
)

REM ####################### 
REM ## MAKE STATIC FILES ##
REM #######################

call echo  **** STARTING BUILD WEB ****
call cd %VG_HOME%\FE
call grunt build%app_env%
call echo  **** FINISH BUILD WEB ****
call echo  **** STARTING BUILD JAR ****

REM ######################## 
REM ## REMOVE ALL FOLDERS ##
REM ########################

call DEL /S /Q %static_src%\*.*
call DEL /S /Q %static_src%\resources
call DEL /S /Q %static_src%\scripts
call DEL /S /Q %static_src%\styles
call xcopy /E %2\*.* %static_src%\
call cd %VG_HOME%\BK

REM #################
REM ##   MAKE JAR  ##
REM #################

call mvn clean package
call cd ..
call echo  **** FINISH TO MAKE JAR !!! Check this in: %VG_HOME%\BK\target\. ****