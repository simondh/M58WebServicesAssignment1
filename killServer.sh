echo Stopping WebChat server

echo stopping webPublisher
if [[ -n $(lsof -ti:8080) ]]; then
  kill -9  `lsof -ti:8080`
fi
jobs

