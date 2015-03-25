clear
echo Building web chat project Simon Hewitt 806068

rm -rf *.class
echo stopping webPublisher
if [[ -n $(lsof -ti:8080) ]]; then
  kill -9  `lsof -ti:8080`
fi
jobs

export CLASSPATH=${CLASSPATH}:.
echo building server
javac -cp webchatclient/ chatserver/*.java
echo Running publisher
java chatserver/WebChatPublisher&
echo Wait for server to start up
sleep 2

cd webchatclient
echo Generate WSDL classes etc
wsimport -p client -keep http://localhost:8080/wc?wsdl
echo Building client
rm *.class
cd ..
javac webchatclient/*.java

echo All built, starting three clients
java webchatclient/WebChatClientForm&
java webchatclient/WebChatClientForm&
java webchatclient/WebChatClientForm&
