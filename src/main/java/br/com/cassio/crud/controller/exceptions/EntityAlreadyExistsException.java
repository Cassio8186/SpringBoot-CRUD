package br.com.cassio.crud.controller.exceptions;

import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;

public class EntityAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3416314487848402995L;

	@SuppressWarnings("rawtypes")
	public EntityAlreadyExistsException(Class entity, String field, String rule) {
		super(LogHelper.construct(LogMessage.ENTITY_ALREADY_EXISTS, entity.getSimpleName(), field, rule));
	}
}
