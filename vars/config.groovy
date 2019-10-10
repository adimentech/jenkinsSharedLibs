import tech.adimen.Pipeline

def call(Map config) {
    Pipeline.instance.reconfigure(config)
}
