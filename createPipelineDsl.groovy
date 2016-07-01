pipelineJob('Jenkins CD Pipeline') {
    definition {
        cps {
            script(readFileFromWorkspace('pipeline.groovy'))
            sandbox()
        }
    }
}