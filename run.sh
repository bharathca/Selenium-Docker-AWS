#Start the grid with 2 chrome containers
docker-compose -f justGrid.yaml up --scale chrome=2 -d
#-d -> it will execute at the background
#-f -> to specify which docker file you want to run 
#scale -> scalling is not but increase/decrease the containers


#Once grid is started, run the test suites with the chrome
Browser=chrome docker-compose -f actualTests.yaml up

# Once it is executed, stop the chrome containers and start it on firefox containers
docker-compose -f justGrid.yaml up --scale firefox=2 -d

#Once grid is started, run the test suites with the firefox
Browser=firefox docker-compose -f actualTests.yaml up


#Once it is executed, stop the firefox containers and start it on edge containers
docker-compose -f justGrid.yaml up --scale edge=2 -d

#Once grid is started, run the test suites with the edge
Browser=edge docker-compose -f actualTests.yaml up

#Now all the executions are done. Bring the grid & test suite docker files to down
docker-compose -f justGrid.yaml down
docker-compose down