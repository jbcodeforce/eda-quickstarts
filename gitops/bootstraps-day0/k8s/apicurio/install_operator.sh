#!/usr/bin/env bash
export NAMESPACE="apicurio"
kubectl create namespace $NAMESPACE
curl -sSL "https://raw.githubusercontent.com/Apicurio/apicurio-registry-operator/v1.0.0/docs/resources/install.yaml" |
sed "s/apicurio-registry-operator-namespace/$NAMESPACE/g" | kubectl apply -f - -n $NAMESPACE
