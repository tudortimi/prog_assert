import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction

class RunExampleTask extends DefaultTask {
    @InputDirectory
    File exampleDir

    @TaskAction
    def run() {
        project.exec {
            executable 'xrun'
            args'-f', project.parent.genFullArgsFile.destination.get().asFile
            args exampleFiles
            args '-incdir', exampleDir
            workingDir project.xrunDir
            project.mkdir workingDir
        }
    }

    def getExampleFiles() {
        def dirPath = exampleDir.path
        def files = new FileNameFinder().getFileNames dirPath,'*.sv'
        return files
    }
}
