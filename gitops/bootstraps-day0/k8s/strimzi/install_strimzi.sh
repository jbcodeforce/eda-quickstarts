echo "Create namespace"
export NS=kafka
kubectl create namespace $NS
echo "Create CRD, ClusterRoles, ClusterRoleBindings"
kubectl create -f 'https://strimzi.io/install/latest?namespace=kafka' -n $NS
kubectl get pod s-n $NS --watch