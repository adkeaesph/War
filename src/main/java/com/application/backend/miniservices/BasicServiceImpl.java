package com.application.backend.miniservices;

public class BasicServiceImpl implements BasicService {

	@Override
	public boolean isNumberPositive(int number) {
		return number > 0;
	}
	
	@Override
	public boolean isNumberNegative(int number) {
		return number < 0;
	}
	
	@Override
	public boolean isCharacterCapitalAlphabet(char character) {
		return character >= 'A' && character <= 'Z';
	}
}
