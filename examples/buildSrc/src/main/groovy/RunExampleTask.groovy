import java.nio.file.Path

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*

abstract class RunExampleTask extends DefaultTask {
    @InputFile
    RegularFileProperty fullXrunArgsFile

    @Input
    abstract Property<Path> getXrunDir()

    @TaskAction
    def run() {
        project.exec {
            executable 'xrun'
            args '-f', fullXrunArgsFile.get()
            args '-top', project.name
            workingDir xrunDir.get()
            project.mkdir workingDir
        }
    }
}
