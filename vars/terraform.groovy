import tech.adimen.Pipeline

def init() {
    sh 'terraform init'
}

def plan(String args = '') {
    sh "terraform plan ${args}"
}

def apply(String args = '') {
    sh "terraform apply ${args}"
}

