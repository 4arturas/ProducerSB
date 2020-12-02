https://github.com/bitnami/charts/tree/master/bitnami/elasticsearch
helm repo add bitnami https://charts.bitnami.com/bitnami
export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
helm install elasticsearch -f values.yaml bitnami/elasticsearch