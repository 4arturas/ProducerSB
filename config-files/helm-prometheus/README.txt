https://www.youtube.com/watch?v=U0pKUQ5qpbs&t=1s
https://github.com/rchidana/K8s-SetUp/blob/main/Monitoring.md

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

helm search repo prometheus

helm install prometheus prometheus-community/prometheus

# create service
kubectl expose service prometheus-server --type=NodePort --target-port=9090 --name=prometheus-server-svc
# get yaml of this exposed service
kubectl get svc prometheus-server-svc -o yaml
# create service yaml
delete created service
kubectl delete svc prometheus-server-svc

cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Service
metadata:
  name: prometheus-svc-ext
spec:
  selector:
    app: prometheus
    component: server
    release: prometheus
  type: NodePort
  ports:
    - port: 80
      targetPort: 9090
      nodePort: 30004
      protocol: TCP
EOF
http://192.168.56.10:30004/

