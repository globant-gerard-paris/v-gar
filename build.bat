@echo off
REM #################################################################################################################################
REM ##   Example use:                                                                                                               #
REM ##   			build [environment] [local | app]   																			#
REM ##																																#
REM ##   [APP_HOME]\build local app  // set config.js to localhost:8080 and put all uncompress static files in public server folder #
REM ##   [APP_HOME]\build dev app    // set config.js to DEV and put all uncompress static files in public server folder.           #
REM ##   [APP_HOME]\build local dist // set config.js to localhost:8080 and put dist static files in public server folder.          #
REM #################################################################################################################################

IF NOT "%1"=="dev"  (
	IF NOT "%1"=="qa"  (
		IF NOT "%1"=="prod"  (
			IF NOT "%1"=="local"  (
				ECHO The first paramter '%1' must be or 'local','dev','qa','prod' values. 
				EXIT /b
			)
		)
	)
) 
IF NOT "%2"=="app"  (
	IF NOT "%2"=="dist"  (
		ECHO The second paramter '%2' must be or 'app' or 'dist' values. 
		EXIT /b
	)
) 

SET static_src=src\main\resources\public

IF ["%1"]==["local"] (
	SET app_env=	
) ELSE (
	SET app_env=:%1
)

REM ####################### 
REM ## MAKE STATIC FILES ##
REM #######################

call echo  **** STARTING BUILD WEB ****
call cd FE
call grunt build%app_env% --force
call echo  **** FINISH BUILD WEB ****
call echo  **** STARTING BUILD JAR ****

REM ######################## 
REM ## REMOVE ALL FOLDERS ##
REM ########################
call cd ..\BK
call DEL /S /Q %static_src%\*.*
call RMDIR /S /Q %static_src%\resources
call RMDIR /S /Q %static_src%\scripts
call RMDIR /S /Q %static_src%\styles

REM ########################### 
REM ## COPY NEW STATIC FILES ##
REM ###########################
call cd ..\FE
call xcopy /E %2\*.* ..\BK\%static_src%\

REM #################
REM ##   MAKE JAR  ##
REM #################
call cd ..\BK
call mvn clean package
call cd ..
call echo  **** FINISH TO MAKE JAR !!! Check this in: BK\target\. ****