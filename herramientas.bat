@echo off
REM Script de mantenimiento para Sabor-Gourmet

echo.
echo ========================================
echo   SABOR GOURMET - HERRAMIENTAS
echo ========================================
echo.

echo Seleccione una opción:
echo.
echo 1. Limpiar y compilar proyecto (mvn clean install)
echo 2. Ejecutar aplicación (mvn spring-boot:run)
echo 3. Limpiar archivos compilados (mvn clean)
echo 4. Descargar dependencias (mvn dependency:resolve)
echo 5. Ver versión de dependencias (mvn dependency:tree)
echo 6. Ejecutar tests (mvn test)
echo 7. Empaquetar WAR (mvn package)
echo.

set /p opcion="Ingrese el número de la opción (1-7): "

if "%opcion%"=="1" (
    echo Limpiando y compilando...
    call mvn clean install
) else if "%opcion%"=="2" (
    echo Iniciando aplicación...
    call mvn spring-boot:run
) else if "%opcion%"=="3" (
    echo Limpiando archivos compilados...
    call mvn clean
) else if "%opcion%"=="4" (
    echo Descargando dependencias...
    call mvn dependency:resolve
) else if "%opcion%"=="5" (
    echo Mostrando árbol de dependencias...
    call mvn dependency:tree
) else if "%opcion%"=="6" (
    echo Ejecutando tests...
    call mvn test
) else if "%opcion%"=="7" (
    echo Empaquetando aplicación...
    call mvn package
) else (
    echo Opción no válida
)

echo.
pause
