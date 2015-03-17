clear
echo Building web chat project Simon Hewitt 806068

rm -rf *.class
echo stopping webPublisher
kill -9  `lsof -ti:8080`
jobs


echo building Publisher
javac WebChatPublisher.java
echo building server
javac chatserver/*.java
echo Running publisher
java WebChatPublisher&
echo Wait for server to start up
sleep 4

cd webchatclient
echo Generate WSDL classes etc
wsimport -p client -keep http://localhost:8080/wc?wsdl
echo Building client
rm *.class
javac WebChatClientForm.java
cd ..

