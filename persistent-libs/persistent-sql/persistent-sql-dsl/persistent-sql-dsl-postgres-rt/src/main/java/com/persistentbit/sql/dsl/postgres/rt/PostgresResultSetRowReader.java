package com.persistentbit.sql.dsl.postgres.rt;

import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.utils.rowreader.ResultSetRowReader;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class PostgresResultSetRowReader extends ResultSetRowReader{

	public PostgresResultSetRowReader(ResultSet rs) {
		super(rs);
	}

	@Override
	public <T> T readNext(Class<T> cls) {
		try {
			if(cls.equals(LocalDate.class)) {
				Date dt = rs.getObject(index++, Date.class);
				if(dt == null) { return null; }

				return (T) dt.toLocalDate();
			}
			/*else if(cls.equals(LocalDateTime.class)) {
				Timestamp ts = rs.getObject(index++, Timestamp.class);
				if(ts == null) { return null; }
				return (T) ts.toLocalDateTime();
			}*/
			else if(cls.equals(PByteList.class)) {
				byte[] bytes = rs.getBytes(index++);
				return (bytes == null
					? null
					: (T)PByteList.from(bytes));
			}
			else if(cls.equals(Short.class)){
				return (T)(Short)rs.getShort(index++);
			} else if(cls.equals(ZonedDateTime.class)){
				OffsetDateTime odt = rs.getObject(index++, OffsetDateTime.class);
				throw new RuntimeException("ToDo");
			}

			return rs.getObject(index++, cls);
		} catch(SQLException e) {
			throw new RuntimeException("SQL error while reading column " + index + " from result set",e);
		}
	}
}
