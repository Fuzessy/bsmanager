események olvasása
fuzessy@fuzessy-Lenovo-G500:~/dev/project/fuz/bs$ /bin/sh ./npm install event-source-polyfill

MYSQL:


jar előállítása:
bulid:
mvn install
 a megfelelő profile alapján
 
Jelszó: 10 körös bcrypt, amit így generálok: 
https://www.browserling.com/tools/bcrypt

fordítás:
package.json
"buildprod": "ng build --prod",

így a pom-ban az npm build paramétere ez lesz:
<arguments>run-script buildprod</arguments> 


 
