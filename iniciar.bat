@echo off
REM Script de inicio rápido para Sabor-Gourmet en Windows

echo.
echo ========================================
echo   SABOR GOURMET - INICIO RAPIDO
echo ========================================
echo.

REM Verificar si Maven está instalado
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Maven no está instalado o no está en el PATH
    echo Descargue Maven desde: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Verificar si Java está instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java no está instalado o no está en el PATH
    echo Descargue Java 17+ desde: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo [✓] Java y Maven encontrados
echo.

REM Compilar proyecto
echo Compilando proyecto...
call mvn clean install
if %errorlevel% neq 0 (
    echo ERROR: La compilación falló
    pause
    exit /b 1
)

echo.
echo [✓] Compilación completada
echo.

REM Ejecutar aplicación
echo Iniciando aplicación Sabor-Gourmet...
echo.
echo La aplicación estará disponible en: http://localhost:8080
echo Para acceder, use cualquiera de estas credenciales:
echo   - Usuario: admin, Contraseña: password
echo   - Usuario: mozo, Contraseña: password
echo   - Usuario: cocinero, Contraseña: password
echo   - Usuario: cajero, Contraseña: password
echo.
echo Presione Ctrl+C para detener la aplicación
echo.

call mvn spring-boot:run

pause
