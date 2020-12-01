https://github.com/bitnami/charts/tree/master/bitnami/logstash/#configuration

helm repo add bitnami https://charts.bitnami.com/bitnami
export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
helm install logstash bitnami/logstash
#helm install logstash -f values.yaml bitnami/logstash
