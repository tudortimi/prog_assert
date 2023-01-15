import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction

class RunExampleTask extends DefaultTask {
    @InputFile
    RegularFileProperty fullXrunArgsFile

    @TaskAction
    def run() {
        project.exec {
            executable 'xrun'
            args '-f', fullXrunArgsFile.get()
            args '-top', project.name
            workingDir project.xrunDir
            project.mkdir workingDir
        }
    }
}
