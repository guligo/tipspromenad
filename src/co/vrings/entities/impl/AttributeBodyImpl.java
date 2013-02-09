package co.vrings.entities.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import co.vrings.entities.AttributeBody;

/**
 * Database entity for {@link AttributeBody}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "body_attributes")
public class AttributeBodyImpl extends AttributeImpl implements AttributeBody {
	
}
