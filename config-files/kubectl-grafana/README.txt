### Grafana
kubectl create deployment grafana --image=docker.io/grafana/grafana:latest

# expose service
kubectl expose deployment grafana --type="NodePort" --port 3000  --name=grafana-svc
# get yaml of this service and create service deployment yaml
kubectl get svc grafana-svc -o yaml
# delete service
kubectl delete svc grafana-svc

cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Service
metadata:
  name: grafana-svc-ext
  labels:
    app: grafana
spec:
  type: NodePort
  ports:
    - port: 3000
      nodePort: 30005
  selector:
    app: grafana
EOF


# grafana credentials
admin:admin