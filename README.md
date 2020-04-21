# Continuous Delivery on Openshift with Tekton and Kustomize

## Deploy the Tekton pipeline and triggers

Before deploying, you will need to update the following files:
* `tekton/base/pipeline/git-secrets.yaml`  
Replace the `password` value with a Personal Access Token. The token will need the `public_repo` and `read:repo_hook` scopes.
* `tekton/base/trigger/ingress.yaml`  
Replace the `host` value with the value for your host, for example `host: eventlistener.apps.mycluster.myorganisation.com`.

Now you can deploy the operator:
```bash
kubectl apply -f tekton/operator-subscription.yaml
```
Once the operator has finished installing, you can apply the pipeline and trigger components:
```bash
kubectl apply -k tekton/overlays
```

## Create the webhook on GitHub

Under the Settings page on the GitHub repository, navigate to Webhooks and apply the following settings:

* **Payload URL**  
Specify the route of the eventlistener. This should be the same as the host in the ingress file or you can find it on OpenShift.  
* **Content type**  
`application/json`  
* **Secret**  
`secret`  
* **Which events would you like to trigger this webhook?**  
`Just the push event.`  
* **Active**  
The checkbox should be ticked.

## Deploy the application

Use the following commands to build the project and deploy it:
```bash
docker build -f Dockerfile -t hello-world .
kubectl apply -k deploy/overlays
```
