import tech.adimen.Pipeline


def cmd(String command, String args = '') {
    def cfg = Pipeline.instance.getConfig('maven')
    withMaven(maven: cfg.tool, mavenSettingsConfig: cfg.settings_config) {
        sh "mvn ${args} ${command}"
    }
}

def compile(String args = '') {
    cmd("clean compile")
}

def build(String args = '') {
    cmd("clean package")
}

def deploy(String args = '') {
    cmd("clean deploy")
}