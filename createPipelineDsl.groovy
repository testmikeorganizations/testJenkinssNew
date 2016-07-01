pipelineJob('Jenkins CD Pipeline') {
    definition {
        cps {
            script(readFileFromWorkspace('ci/pipeline.groovy'))
            sandbox()
        }
    }
}