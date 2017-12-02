package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public class TCountry extends DTableExprImpl<Country>{

	public final DExprInt id;
	public final DExprString name;
	public final DExprString code;

	public TCountry(DExprInt id, DExprString name, DExprString code) {
		super(
			PList.val(id,name,code),
			_scon -> _rr -> {
				Integer	_id = DImpl._get(id)._read(_scon,_rr);
				String	_name = DImpl._get(name)._read(_scon,_rr);
				String	_code = DImpl._get(code)._read(_scon,_rr);
				if(_id== null && _name== null && _code== null) { return null; }
				return new Country(_id,_name,_code);
			}
		);

		this.id = id;
		this.name = name;
		this.code = code;
	}

	@Override
	protected TCountry _doWithAlias(String alias) {
		return new TCountry(
			(DExprInt)DImpl._get(id)._withAlias(alias),
			(DExprString)DImpl._get(name)._withAlias(alias),
			(DExprString)DImpl._get(code)._withAlias(alias)
		);
	}

}
