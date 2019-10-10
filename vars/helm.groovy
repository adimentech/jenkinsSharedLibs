import tech.adimen.Pipeline

def install(name, namespace, chart, String args = '') {
    upgrade(name, namespace, chart, args)
}

def upgrade(name, namespace, chart, String args = '') {
    k8s.with {
        sh "helm upgrade --install --wait ${name} ${chart} ${args} --namespace ${namespace}"
    }
}

def delete(name, String namespace = 'default') {
    k8s.with {
        sh "helm delete --purge ${name}"
    }
}

def init() {
    def cfg = Pipeline.instance.getConfig('helm')

    sh 'helm init --client-only'
    for (repo in cfg.repositories) {
        sh "helm repo add ${repo.name} ${repo.URL}"
    }
}

def version() {
    sh 'helm version'
}