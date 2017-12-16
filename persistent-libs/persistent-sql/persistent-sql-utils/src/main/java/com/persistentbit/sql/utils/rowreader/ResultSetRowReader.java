package com.persistentbit.sql.utils.rowreader;

import com.persistentbit.collections.PByteList;

import java.sql.*;
import java.time.*;

/**
 * RowReader implementation that
 * reads from a {@link ResultSet}
 *
 * @author Peter Muys
 * @since 3/10/16
 */
public class ResultSetRowReader implements RowReader{

	protected final ResultSet rs;
	protected int index = 1;

	public ResultSetRowReader(ResultSet rs) {
		this.rs = rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T readNext(Class<T> cls) {
		try {
			if(cls.equals(LocalDate.class)) {
				Date dt = rs.getObject(index++, Date.class);
				if(dt == null) { return null; }

				return (T) dt.toLocalDate();
			}
			else if(cls.equals(LocalDateTime.class)) {
				Timestamp ts = rs.getTimestamp(index++);
				if(ts == null) { return null; }
				return (T) ts.toLocalDateTime();
			}
			else if(cls.equals(PByteList.class)) {
				byte[] bytes = rs.getBytes(index++);
				return (bytes == null
					? null
					: (T)PByteList.from(bytes));
			}
			else if(cls.equals(Short.class)){
				return (T)(Short)rs.getShort(index++);
			}
			else if(cls.equals(ZonedDateTime.class)){
				Timestamp ts = rs.getTimestamp(index++);
				if(ts == null) { return null; }
				return (T) ZonedDateTime.of(ts.toLocalDateTime(), ZoneId.systemDefault());
			} else if(cls.equals(LocalTime.class)){
				Time t = rs.getTime(index++);
				return (T)t.toLocalTime();
			}

			return rs.getObject(index++, cls);
		} catch(SQLException e) {
			throw new RuntimeException("SQL error while reading column " + index + " from result set",e);
		}

	}


	public ResultSetRowReader nextRow() {
		index = 1;
		return this;
	}
}
