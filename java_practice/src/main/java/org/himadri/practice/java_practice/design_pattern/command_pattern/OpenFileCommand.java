
package org.himadri.practice.java_practice.design_pattern.command_pattern;

public class OpenFileCommand implements Command {

	private FileSystemReceiver fileSystem;
	
	public OpenFileCommand(FileSystemReceiver fs){
		this.fileSystem=fs;
	}
	@Override
	public void execute() {
		//open command is forwarding request to openFile method
		this.fileSystem.openFile();
	}

}
