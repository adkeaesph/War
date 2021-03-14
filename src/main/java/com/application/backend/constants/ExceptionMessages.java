package com.application.backend.constants;

public class ExceptionMessages {
	public static final String INVALID_CHOICE_MESSAGE = "Choice must be either 0  or 1.";
	public static final String NUMERICAL_CHOICE_MESSAGE = "Choice must be a number.";
	public static final String INVALID_INPUT_FORMAT_MESSAGE = "Invalid format of input."
			+ "\nAn example of valid input format - \"FALICORNIA_ATTACK 40H 20E 5AT 2SG\"";
	public static final String INVALID_COMMAND_FORMAT_MESSAGE = "Only one argument - pathfile is allowed"
			+ "\nAn example of valid command format - \"java -jar geektrust.jar <path_to_file>\"";
	public static final String INVALID_RELATIVE_FILEPATH_MESSAGE = "File not found in the specified path."
			+ "\nOnly correct relative paths are allowed as argument."
			+ "\n(If your input file is 2 levels above your present directory, give path as ../../<your_file>)";
	public static final String INVALID_QUANTITY_MESSAGE = "Number of units of a battalion must be a whole number.";
	public static final String NON_NEGATIVE_QUANTITY_MESSAGE = "Number of units of a battalion cannot be negative.";
	public static final String INVALID_FILE_EXTENSION_MESSAGE = "Input files only with .txt extension are allowed.";
}
