This is a README file which tells how to run this application as a jar.

Steps:
1) Extract the War.zip file. Let the extracted folder be "War".
2) Inside the folder "War", you will get the file -> "geektrust.jar"
3) Open a terminal in this directory.
4) Run the command -> java -jar geektrust.jar <relative path to your input file>
5) You can use the sample input files in the "/src/main/resources" directory.
	Eg. java -jar geektrust.jar /src/main/resources/SAMPLE_INPUT_3.txt
	
6) If you have your input file outside the "War" folder, you can give the relative path as follows:
	If your input file is one level above "War" folder, your command will be -
	java -jar geektrust.jar ../<your_input_file>
	
	If your input file is two levels above "War" folder, your command will be -
	java -jar geektrust.jar ../../<your_input_file>
	
	and so on
	Note - You can traverse to any directory by giving a relative path similar to Linux/Unix file system
	
7) If you do not pass any argument, the relative path specified by "INPUT_FILE_RELATIVE_PATH" constant variable of 
   "src.main.java.com.application.backend.constants.InputOutputConstants.java" class will be considered. 