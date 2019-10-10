@Grab('io.kubernetes:client-java:4.0.0')
import io.kubernetes.client.ApiClient
import io.kubernetes.client.Configuration
import io.kubernetes.client.apis.CoreV1Api
import io.kubernetes.client.models.V1NamespaceList
import io.kubernetes.client.models.V1Namespace
import io.kubernetes.client.util.Config

ApiClient client = Config.defaultClient()
Configuration.setDefaultApiClient(client)

CoreV1Api api = new CoreV1Api()

V1NamespaceList list = api.listNamespace(null, null, null, 'metadata.name!=kube-system,metadata.name!=kube-public', null, null, null, null, null)
List namespaces = []
for (V1Namespace item : list.getItems()) {
    namespaces.add(item.getMetadata().getName())
}

return namespaces