https://github.com/bitnami/charts/tree/master/bitnami/elasticsearch
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install elasticsearch -f values.yaml bitnami/elasticsearch