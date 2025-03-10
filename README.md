# Theia.cloud

The goal of Theia.cloud is to simplify the deployment of Theia-based (and similar) products on Kubernetes. We follow a convention over configuration approach allowing users to get started fast. At the same time, we aim for extensibility allowing developers to customize all aspects of the kubernetes deployment as required.

## Feedback, Help and Support

If you encounter any problems feel free to [open an issue](https://github.com/eclipsesource/theia-cloud/issues/new/choose) on the repo.
For questions and discussions please use the [the Github discussions](https://github.com/eclipsesource/theia-cloud/discussions).
You can also reach us via [email](mailto:support@theia-cloud.io?subject=Theia.cloud).
In addition, EclipseSource also offers [professional support](https://eclipsesource.com/services/developer-support/) for Theia and Theia.cloud.

## Components

Theia.cloud consists of the following components.

### Kubernetes Custom Resource Definitions and Operator

Theia.cloud brings simple custom resource definitions (CRDs) that allow to specify the required configuration, like the docker image of the Theia-based product.\
A Java-based operator will listen for the creation, modification, and deletion of custom resources based on those CRDs and will manage the application.\
See [Architecture.md](doc/docs/Architecture.md) for more information on the architecture.

### Theia.cloud Service

This REST Service acts as the API for creating and stopping Theia-based products for an authenticated user as well as providing additional information.\
The Theia.cloud service creates, modifies, and deletes the custom resources the operator listens to.

### Sample Dashboard and resusable JS/UI components

Theia.cloud comes with a basic landing page/dashboard which allows to launch sessions.

We offer a common ts library for the API of the Theia.cloud service, which may be resued by clients to write their own custom dashboards.

We plan to offer reusable ui components in the future as well.

## Building

All components are deployed as docker images and may be built with docker. See [Building.md](doc/docs/Building.md) for more information. We offer prebuilt images ready to use.

## Installation

We offer a helm chart under `helm/theia.cloud` which may be used to install Theia.cloud. Please check our getting started guides below as well, which will explain the possible values in more detail.

```bash
helm install theia-cloud ./helm/theia.cloud --namespace theiacloud --create-namespace
# Optional: switch to the newly created namespace
kubectl config set-context --current --namespace=theiacloud

# Uninstall
helm uninstall theia-cloud -n theiacloud
```

### Getting started with

[...Minikube](doc/docs/platforms/Minikube.md)

[...configuring the Keycloak Realm](doc/docs/Keycloak.md)
