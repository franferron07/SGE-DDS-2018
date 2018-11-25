# dds-tp2018-entrega3-tips
Mini Pruebas de concepto para la entrega 3

## REST

En 2 ventanas separadas:

mvn clean package  exec:java -Dexec.mainClass="ar.edu.dds.ServerREST"

mvn clean package  exec:java -Dexec.mainClass="ar.edu.dds.ClientREST"

("clean package" pueden quitarse si no hubo cambios en el codigo)

## MQTT

1. Prerequisitos: instalar algun broker de mensajeria MQTT. 
Para hacer estas pruebas se utilizo Moquette y es muy sencillo de levantar.
   1. descargar https://bintray.com/artifact/download/andsel/generic/moquette-distribution-0.11.tar
   1. descomprimirlo en alguna carpeta, por ejemplo $HOME/dds
   1. ir a $HOME/dds/moquette-distribution-0.11/
   1. crear la carpeta "config"
   1. dentro de la carpeta $HOME/dds/moquette-distribution-0.11/config/ descargar 
https://raw.githubusercontent.com/andsel/moquette/master/broker/config/moquette.conf
   1.  iniciar el servidor MOQUETTE 
       1. ir al directorio $HOME/dds/moquette-distribution-0.11/ (desde donde se ejecuta busca config/moquette.conf)
       1. Ejecutar bin/moquette-distribution (Hay un .bat para windows, aunque no lo probamos. Probablemente requiera alguna configuración extra) 
       1. Es importante aclarar que "config/moquette.conf" se busca relativo a donde se esta parado a la hora de ejecutar moquette-distribution. Si lo ejecutan como en las intrucciones lo busca en  $HOME/dds/moquette-distribution-0.11/config/moquette.conf. Si ejecutan moquette-distribution directamente desde bin, lo buscara en $HOME/dds/moquette-distribution-0.11/bin/config/moquette.conf
2. En  2 ventanas separadas 

   1. mvn clean package  exec:java -Dexec.mainClass="ar.edu.dds.SubscriberMQTT"
   1. mvn clean package  exec:java -Dexec.mainClass="ar.edu.dds.PublisherMQTT"
   1. recordar que ambas aplicaciones son clientes del servidor de mensajes Moquette. Si este no esta levantado les va a arrojar un error



Los ejemplos de MQTT se tomaron de:
https://github.com/tgrall/mqtt-sample-java



