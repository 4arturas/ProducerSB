https://github.com/bitnami/charts/tree/master/bitnami/kibana/#installing-the-chart
helm repo add bitnami https://charts.bitnami.com/bitnami
export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
helm install kibana --set elasticsearch.hosts[0]=<Hostname of your ES instance> --set elasticsearch.port=<port of your ES instance> bitnami/kibana
helm install kibana -f values.yaml --set elasticsearch.hosts[0]="192.168.56.10" --set elasticsearch.port="32323" bitnami/kibana
helm install kibana -f kv.yaml --set elasticsearch.hosts[0]="192.168.56.10" --set elasticsearch.port="32323" bitnami/kibana