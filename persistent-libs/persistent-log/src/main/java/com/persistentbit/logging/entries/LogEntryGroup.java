package com.persistentbit.logging.entries;


import com.persistentbit.code.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 30/12/16
 */
public class LogEntryGroup extends AbstractLogEntry{
	@Nullable
	private final List<LogEntry> entries;
	private final int size;

	public LogEntryGroup(@Nullable List<LogEntry> entries) {
		this.entries = entries;
		this.size = entries == null ? 0 : entries.size();
	}



	public static LogEntryGroup empty() {
		return new LogEntryGroup(null);
	}

	@Override
	public LogEntryGroup append(LogEntry other) {
		if(other.isEmpty()){
			return this;
		}
		if(size == 0){
			List<LogEntry> newList = new ArrayList<>();
			newList.add(other);
			return new LogEntryGroup(newList);
		}
		synchronized(entries){
			if(entries.size() == size){
				entries.add(other);
				return new LogEntryGroup(entries);
			}
			List<LogEntry> newList = entries.subList(0,size);
			newList.add(other);
			return new LogEntryGroup(newList);
		}
	}

	@Override
	public Optional<LogContext> getContext() {
		if(size == 0){
			return Optional.empty();
		}
		return entries.get(0).getContext();
	}

	public List<LogEntry> getEntries() {
		return entries == null
			? Collections.emptyList()
			: Collections.unmodifiableList(entries);
	}


}
