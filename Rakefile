require 'rake'
require 'fileutils'
require 'zip/zip'
 
VERBOSE = ENV['VERBOSE'] || false
PROJECT_NAME = 'episodic-platform-sdk-java'
PROJECT_VERSION = '0.9'
 
desc 'Clean project, compile java source and run tests'
task :default => [:clean, :compile]
 
desc 'Clean project'
task :clean do |t|
  puts '>>>> cleaning project <<<<'
  FileUtils.rm_r 'build', :force => true, :verbose => VERBOSE
  FileUtils.rm_r 'bin', :force => true, :verbose => VERBOSE
  FileUtils.rm_r 'doc', :force => true, :verbose => VERBOSE
end
 
desc 'Clobber project'
task :clobber => :clean do |t|
  puts '>>>> clobbering project <<<<'
  FileUtils.rm FileList["#{PROJECT_NAME.downcase}*.jar"].to_s, :force => true, :verbose => VERBOSE
  FileUtils.rm FileList["#{PROJECT_NAME.downcase}*.zip"].to_s, :force => true, :verbose => VERBOSE
end
 
desc 'Compile java source'
task :compile do |t|
  puts '>>>> compiling java source <<<<'
  FileUtils.mkdir_p 'build/java', :verbose => VERBOSE
  command = "javac -classpath #{compile_classpath} -d build/java #{java_source}"
  puts command
  execute command
end

desc 'Generate java docs'
task :doc do |t|
  puts '>>>> generating java docs <<<<'
  FileUtils.mkdir_p 'doc', :verbose => VERBOSE
  command = "javadoc -sourcepath . #{java_source} -classpath #{compile_classpath} -d doc"
  puts command
  execute command
end
 
desc 'Package project'
task :package => [:clobber, :compile, :doc] do |t|
  puts '>>>> packaging project <<<<'
  execute "jar cf #{PROJECT_NAME.downcase}.jar -C build/java ."
  Zip::ZipFile.open("#{PROJECT_NAME.downcase}-#{PROJECT_VERSION}.zip", Zip::ZipFile::CREATE) do |zipfile|
    zipfile.add("#{PROJECT_NAME.downcase}-#{PROJECT_VERSION}.jar","#{PROJECT_NAME.downcase}.jar")
    zipfile.add('LICENSE','LICENSE')
    zipfile.add('README','README.md')
  end
end
 
def compile_classpath
  Dir['lib/*.jar'].join(File::PATH_SEPARATOR)
end

def java_source
  FileList['src/main/java/**/*.java'].to_s
end
 
def execute(command)
  puts command if VERBOSE
  system command
end