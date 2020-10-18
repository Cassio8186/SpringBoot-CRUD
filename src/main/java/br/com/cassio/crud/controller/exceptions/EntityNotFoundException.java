package br.com.cassio.crud.controller.exceptions;

import br.com.cassio.crud.helper.LogHelper;
import br.com.cassio.crud.helper.enumeration.LogMessage;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4164430121080969278L;

	@SuppressWarnings("rawtypes")
	public EntityNotFoundException(Class entity, String field, Object value) {
		super(LogHelper.construct(LogMessage.EXCEPTION_ENTITY_NOT_FOUND, entity.getSimpleName(), field, value));

	}
}
