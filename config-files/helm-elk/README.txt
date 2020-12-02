export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
helm install elk-auto . --set global.hostIp=192.168.56.10