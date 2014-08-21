package de.tlercher.punchclock.orm.repositorys;

import de.tlercher.punchclock.orm.entitys.Entry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "entrys", path = "entrys")
public interface EntryRepository extends PagingAndSortingRepository<Entry, Long> {

    List<Entry> findByEntryDate(@Param("entryDate") Date date);

    /**
     * Can be used for filtering for automatically created descriptions.
     *
     * @param description
     * @return
     */
    List<Entry> findByDescription(@Param("description") String description);
}
