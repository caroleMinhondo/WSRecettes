REM click droit open with text editor pour saisir contenu .bat sous eclipse
REM click droit open with system editor pour lancer execution du .bat sous eclipse

cd /d "%~dp0"
REM set WSDL_URL=http://localhost:7979/serveurSoapD/ConvertisseurImpl?wsdl
set WSDL_URL=http://localhost:8080/springBootWebService/service/convertisseur?wsdl
wsimport -keep -d ./main/java %WSDL_URL%

REM le serveur doit être démarré pour que ?wsdl soit accessible
REM effectuer un "refresh" sour eclipse sur le projet clientWS

pause