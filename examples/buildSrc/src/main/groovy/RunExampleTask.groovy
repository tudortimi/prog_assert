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
            args'-f', fullXrunArgsFile.get()
            args exampleFiles
            args '-incdir', project.projectDir
            workingDir project.xrunDir
            project.mkdir workingDir
        }
    }

    def getExampleFiles() {
        def dirPath = project.projectDir.path
        def files = new FileNameFinder().getFileNames dirPath,'*.sv'
        return files
    }
}
