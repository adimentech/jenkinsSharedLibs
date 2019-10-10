import tech.adimen.Pipeline

def analyze() {
    def cfg = Pipeline.instance.getConfig('sonar')
    withSonarQubeEnv(cfg.server) {
        mvnHome = tool 'M3'
        sh "'${mvnHome}/bin/mvn' org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
    }
}
