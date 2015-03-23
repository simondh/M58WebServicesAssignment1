clear
echo Building web chat project Simon Hewitt 806068

rm -rf *.class
echo stopping webPublisher
if [[ -n $(lsof -ti:8080) ]]; then
  kill -9  `lsof -ti:8080`
fi
jobs


echo building Publisher
export CLASSPATH=${CLASSPATH}:.
#echo $CLASSPATH
javac -cp . WebChatPublisher.java
echo building server
javac -cp webchatclient/ chatserver/*.java
echo Running publisher
java WebChatPublisher&
echo Wait for server to start up
sleep 2

cd webchatclient
echo Generate WSDL classes etc
wsimport -p client -keep http://localhost:8080/wc?wsdl
echo Building client
rm *.class
javac WebChatClientForm.java
cd ..

