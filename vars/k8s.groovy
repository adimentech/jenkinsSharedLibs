import tech.adimen.Pipeline

def with(Closure body) {
    def cfg = Pipeline.instance.getConfig('kubernetes')
    withKubeConfig([credentialsId: cfg.credentialsId, serverUrl: cfg.serverUrl]) {
        body()
    }
}
def getNamespaces() {
    with {
        def namespaces = sh(
                script: "kubectl get ns -o=jsonpath='{.items[*].metadata.name}'",
                returnStdout: true
        )
        return namespaces.tokenize()
    }
}
