
# Springboot + RabbitMQ + Mongodb + Kubernetes App

### Overview
This project is a very simple demonstration of the interplay of springboot microservice, rabbitmq and mongodb in kubernetes cluster

### Dependecies
You need to have Minikube, JDK, Gradle, Git Bash installed on your system
Minikube is up and running

### Setup

#### Steps for Microservice container

1. Move into micro-service-one dir

`cd micro-service-one`

2. Build Microservices

`gradle build`

3. Match docker and Minikube env

`eval $(minikube docker-env)`

4. Create Image

`docker build -t micro-service-one:v1 .`

5. Deploy microservice in kubernetes

`kubectl run micro-service-one --image=micro-service-one:v1 --port=8080`

6. Expose microservice as a service

`kubectl expose deployment micro-service-one --type=NodePort`

7. Access Service

`minikube service micro-service-one`

#### Steps for MongoDB container

1. Deploy mongodb in kubernetes

`kubectl run mongo --image=mongo:latest --port=27017`

2. Expose Mongodb as a service

`kubectl expose deployment mongo`


#### Steps for RabbitMQ container

1. Deploy RabbitMQ in kubernetes

`kubectl run rabbitmq --image=rabbitmq:latest --port=5672`

2. Expose Mongo as a service

`kubectl expose deployment rabbitmq`

### Test

Here are the steps to test the application

1. Access the micro service. This would open the service in the browser. Make the note of ip and port
`minikube service micro-service-one`

2. Access getAllTrades endpoint. This would return blank array initially
http://<IP>:<PORT>/getAllTrades

3. Save Trades using a rest client like postman.

URL: http://192.168.99.100:31993/create

Http-Method: Post

ContentType: Application/Json

Body:
    {
        "tradeId": "ABCD",
        "tradeDate": "2017-02-01",
        "quantity":120,
        "price": 400,
        "location": "NY",
        "commodity": "AL",
        "counterparty": "Lorem"
    }

4. Access getAllTrades endpoint. This should return the saved trade
http://<IP>:<PORT>/getAllTrades


