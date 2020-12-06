helm repo add bitnami https://charts.bitnami.com/bitnami
helm install kafka bitnami/kafka -f values.yaml

D:\kafka_2.13-2.6.0\bin\windows\kafka-console-producer --topic example-topic --bootstrap-server 192.168.56.10:30006
D:\kafka_2.13-2.6.0\bin\windows\kafka-console-consumer --topic example-topic --bootstrap-server 192.168.56.10:30006