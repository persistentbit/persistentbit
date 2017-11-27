package com.persistentbit.sql.utils.rowreader;

import com.persistentbit.collections.PByteList;
import com.persistentbit.result.Result;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * RowReader implementation that
 * reads from a {@link ResultSet}
 *
 * @author Peter Muys
 * @since 3/10/16
 */
public class ResultSetRowReader implements RowReader{

	private final ResultSet rs;
	private int index = 1;

	public ResultSetRowReader(ResultSet rs) {
		this.rs = rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Result<T> readNext(Class<T> cls) {
		return Result.function(cls).code(l -> {
			try {
				if(cls.equals(LocalDate.class)) {
					Date dt = rs.getObject(index++, Date.class);
					if(dt == null) { return Result.empty(); }

					return Result.success((T) dt.toLocalDate());
				}
				else if(cls.equals(LocalDateTime.class)) {
					Timestamp ts = rs.getObject(index++, Timestamp.class);
					if(ts == null) { return Result.empty(); }
					return Result.success((T) ts.toLocalDateTime());
				}
				else if(cls.equals(PByteList.class)) {
					byte[] bytes = rs.getBytes(index++);
					return (bytes == null
						? Result.empty()
						: Result.success((T)PByteList.from(bytes)));
				}

				return Result.success(rs.getObject(index++, cls));
			} catch(SQLException e) {
				return Result.failure(new RuntimeException("SQL error while reading column " + index + " from result set",e));
			}

		});

	}


	public ResultSetRowReader nextRow() {
		index = 1;
		return this;
	}
}
