import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class RunExampleTask extends DefaultTask {
    @Input
    String exampleName

    @TaskAction
    def run() {
        project.exec {
            executable 'xrun'
            args'-f', project.parent.genFullArgsFile.destination.get().asFile
            args exampleFiles
            workingDir project.xrunDir
            project.mkdir workingDir
        }
    }

    def getExampleFiles() {
        def dirPath = project.file(exampleName).path
        def files = new FileNameFinder().getFileNames dirPath,'*.sv'
        return files
    }
}
