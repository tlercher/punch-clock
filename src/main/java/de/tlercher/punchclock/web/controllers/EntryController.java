package de.tlercher.punchclock.web.controllers;

import de.tlercher.punchclock.orm.entitys.Entry;
import de.tlercher.punchclock.orm.repositorys.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/entrys")
public class EntryController {
    private EntryRepository entryRepository;


    @Autowired
    public EntryController(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @RequestMapping("/")
    public @ResponseBody Iterable<Entry> listEntrys() {
        return entryRepository.findAll(new Sort(Sort.Direction.DESC, "entryDate"));
    }

    @RequestMapping("/types")
    public @ResponseBody List<Entry.Type> listTypes() {
        return Arrays.asList(Entry.Type.values());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody Entry addEntry(@RequestBody @Valid Entry entry) {
        return entryRepository.save(entry);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Entry getEntry(@PathVariable(value = "id") Long id) {
        return entryRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT})
    public @ResponseBody Entry updateEntry(@PathVariable(value = "id") Long id, @RequestBody @Valid Entry entry) {
        // Using the pathVariable instead of entry, because the RequestBody can be null
        Entry currEntry = entryRepository.findOne(id);

        // Copy updateable properties
        currEntry.setType(entry.getType());
        currEntry.setEntryDate(entry.getEntryDate());
        currEntry.setDescription(entry.getDescription());

        entryRepository.save(currEntry);

        return currEntry;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteEntry(@PathVariable(value = "id") Long id) {
        entryRepository.delete(id);
    }
}
