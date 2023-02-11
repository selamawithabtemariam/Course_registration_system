package edu.mum.se.mumsched.service;

import java.util.List;

import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.service.exception.NotFoundException;

public interface EntryService {
	void addEntry(Entry entry);
	Entry getEntry(Integer id) throws NotFoundException;
	List<Entry> getAllEntries();
	void deleteEntry(Integer entryId);
	void editEntry(Entry entry);
	

}
