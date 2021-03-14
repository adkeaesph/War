package com.application.backend.services;

import com.application.backend.constants.ExceptionMessages;
import com.application.backend.constants.CommonConstants;
import com.application.backend.constants.DefenceConstants;
import com.application.backend.domains.defenses.Army;
import com.application.backend.domains.defenses.Battalion;
import com.application.backend.exceptions.InvalidQuantityException;
import com.application.backend.miniservices.BasicService;
import com.application.backend.miniservices.BasicServiceImpl;

public class ParserServiceImpl implements ParserService {

	@Override
	public Army extractAndIncludeDefaultBattalion(Army army, String battalionString, int ranking)
			throws NumberFormatException, InvalidQuantityException {

		return extractAndIncludeBattalion(army, battalionString, DefenceConstants.FALICORNIAN_UNIT_STRENGTH, ranking);
	}

	@Override
	public Army extractAndIncludeBattalion(Army army, String battalionString, int powerPerUnit, int ranking)
			throws NumberFormatException, InvalidQuantityException {
		CategoryInfo categoryInfo = getCategoryInfo(battalionString);
		int quantity = getQuantity(battalionString, categoryInfo.lastIndexOfQuantity);
		army.addBattalion(categoryInfo.category, new Battalion(quantity, powerPerUnit, ranking));
		return army;
	}

	private CategoryInfo getCategoryInfo(String battalionString) {
		BasicService basicService = new BasicServiceImpl();
		String category = CommonConstants.EMPTY_STRING;
		int index;
		for (index = battalionString.length() - 1; index >= 0; index--) {
			if (basicService.isCharacterCapitalAlphabet(battalionString.charAt(index)))
				category = battalionString.charAt(index) + category;
			else
				break;
		}
		return new CategoryInfo(category, index);
	}

	private int getQuantity(String battalionString, int lastIndexOfQuantity) throws InvalidQuantityException {
		String quantityString = CommonConstants.EMPTY_STRING;
		for (int index = 0; index <= lastIndexOfQuantity; index++)
			quantityString += battalionString.charAt(index);

		int quantity = 0;
		try {
			quantity = Integer.parseInt(quantityString);
		} catch (NumberFormatException exception) {
			throw new InvalidQuantityException(ExceptionMessages.INVALID_QUANTITY_MESSAGE);
		}

		if (quantity < 0) {
			throw new InvalidQuantityException(ExceptionMessages.NON_NEGATIVE_QUANTITY_MESSAGE);
		}
		return quantity;
	}

	private class CategoryInfo {
		private String category;
		private int lastIndexOfQuantity;

		CategoryInfo(String category, int lastIndexOfQuantity) {
			this.category = category;
			this.lastIndexOfQuantity = lastIndexOfQuantity;
		}
	}

}
