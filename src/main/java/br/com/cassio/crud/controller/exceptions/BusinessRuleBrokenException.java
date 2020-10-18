package br.com.cassio.crud.controller.exceptions;

import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;

public class BusinessRuleBrokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3416314487848402995L;

	@SuppressWarnings("rawtypes")
	public BusinessRuleBrokenException(Class entity, String field, String rule) {
		super(LogHelper.construct(LogMessage.EXCEPTION_BUSINESS_RULE_BROKEN, entity.getSimpleName(), field, rule));
	}
}
