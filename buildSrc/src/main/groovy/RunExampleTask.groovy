import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.api.tasks.options.OptionValues

class RunExampleTask extends DefaultTask {
    @Option(option = 'name', description = 'name of the example to run')
    String exampleName

    @OptionValues('name')
    def List<String> getAvailableExamples() {
	def exampleNames = []
        project.file('.').eachDir {
	        exampleNames << it.name
        }
        return exampleNames
    }

    @TaskAction
    def run() {
        checkExampleName()
        project.exec {
            executable 'xrun'
            args'-f', project.parent.genFullArgsFile.destination.get().asFile
            args exampleFiles
            workingDir project.xrunDir
            project.mkdir workingDir
        }
    }

    def checkExampleName() {
        def isLegal = exampleName in getAvailableExamples()
        if (!isLegal)
            throw new InvalidUserDataException('illegal example name')
    }

    def getExampleFiles() {
        def dirPath = project.file(exampleName).path
        def files = new FileNameFinder().getFileNames dirPath,'*.sv'
        return files
    }
}
