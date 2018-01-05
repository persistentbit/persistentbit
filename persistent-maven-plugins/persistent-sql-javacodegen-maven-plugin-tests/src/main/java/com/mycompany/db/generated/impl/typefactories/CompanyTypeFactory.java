package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.ECompany;
import com.mycompany.db.generated.values.Company;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class CompanyTypeFactory extends AbstractStructureTypeFactory<ECompany, Company>{

	private class ECompanyImpl extends ECompany implements StructTypeImplMixin<ECompany, Company>{


		public ECompanyImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EInt) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (ELong) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<ECompany, Company> getTypeFactory() {
			return CompanyTypeFactory.this;
		}

		@Override
		public String toString() {
			return "ECompany[" + id + companyName + adresStreet + adresHouseNumber + adresBusNumber + adresPostalcode + adresCity + adresCountry + ownerPersonId + "]";
		}

		@Override
		public ECompany getThis() {
			return this;
		}
	}

	public CompanyTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<ECompany, Company>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(EString.class, "company_name", "companyName", v -> v.getCompanyName(), v -> v.companyName)
			, createField(EString.class, "adres_street", "adresStreet", v -> v.getAdresStreet(), v -> v.adresStreet)
			, createField(EInt.class, "adres_house_number", "adresHouseNumber", v -> v
				.getAdresHouseNumber(), v -> v.adresHouseNumber)
			, createField(EString.class, "adres_bus_number", "adresBusNumber", v -> v.getAdresBusNumber()
				.orElse(null), v -> v.adresBusNumber)
			, createField(EString.class, "adres_postalcode", "adresPostalcode", v -> v
				.getAdresPostalcode(), v -> v.adresPostalcode)
			, createField(EString.class, "adres_city", "adresCity", v -> v.getAdresCity(), v -> v.adresCity)
			, createField(EString.class, "adres_country", "adresCountry", v -> v.getAdresCountry(), v -> v.adresCountry)
			, createField(ELong.class, "owner_person_id", "ownerPersonId", v -> v.getOwnerPersonId()
				.orElse(null), v -> v.ownerPersonId)
		);
	}

	@Override
	protected Company buildValue(Object[] fieldValues) {
		return new Company(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
			, (Integer) fieldValues[3]
			, (String) fieldValues[4]
			, (String) fieldValues[5]
			, (String) fieldValues[6]
			, (String) fieldValues[7]
			, (Long) fieldValues[8]
		);
	}

	@Override
	protected ECompanyImpl createExpression(PStream<DExpr> fieldValues) {
		return new ECompanyImpl(fieldValues.iterator());
	}

	@Override
	public Class<ECompany> getTypeClass() {
		return ECompany.class;
	}
}
