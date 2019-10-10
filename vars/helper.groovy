import groovy.text.StreamingTemplateEngine

def renderTemplate(input, variables) {
    def engine = new StreamingTemplateEngine()
    return engine.createTemplate(input).make(variables).toString()
}

def fileFromTemplate(fileName, templatePath, variables) {
    def template = libraryResource(templatePath)
    def output = renderTemplate(template, variables)
    writeFile file: fileName, text: output
}
