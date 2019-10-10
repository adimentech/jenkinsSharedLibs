import groovy.transform.Field
import tech.adimen.Pipeline

def install(name, namespace, chart, args = '') {
    upgrade(name, namespace, chart, args)
}

def upgrade(name, namespace, chart, args = '') {
    k8s.with {
        sh "helm upgrade --install --wait ${name} ${chart} ${args} --namespace ${namespace}"
    }
}

def delete(name, namespace = 'default') {
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

def test() {
    sh 'helm version'
}