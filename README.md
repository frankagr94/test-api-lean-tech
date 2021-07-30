# test-api-lean-tech
An API about positions and employees for technical testing purposes

Create positions,
Create and associate employees to a position, 
Update and realocate to another position to an existing employee, 
Delete an existing employee

POSTMAN COLLECTION: 
https://www.getpostman.com/collections/4056a3d98972e2b218c4

DOCKER COMMANDS:
docker build -f test-api-lean-tech/Dockerfile test-api-lean-tech/ -t frankagr94/test-api-lean-tech:v1
docker push frankagr94/test-api-lean-tech:v1
docker run -it --name test-api-lean-tech -p 80:8080 frankagr94/test-api-lean-tech:v1


Develop By Ing. Frank Gonzalez - Venezuela
