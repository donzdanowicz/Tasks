call .\runcrud.bat
if "%ERRORLEVEL%" == "0" goto showtasks
echo.
echo runcrud has errors - breaking work
goto fail

:showtasks
timeout /T 10
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo showtasks has errors - breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Showtasks finished.