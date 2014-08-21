package de.tlercher.punchclock.orm.entitys;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * This Class represents a entry in our PunchClock (Come, Leave or just a Marker)
 */
@Entity
public class Entry extends AbstractPersistable<Long> {

    /**
     * Represents the given type for our Entry.
     *
     * For a check-in {@link Entry.Type#Come},
     * check-out {@link Entry.Type#Leave}
     * and {@link Entry.Type#Marker} for milestones like (as in "Part 1 of 3 in XYZ").
     *
     */
    public enum Type {
        Come,
        Leave,
        Marker
    }


    // I omit a relation to a Person/User here because i didn't want to implement this…\

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    /**
     * Primary useful for {@link Entry.Type#Marker}s, like "Implemented angularjs controllers", but can be also used for
     * things like "Automatically created by PunchClock for iOS".
     */
    private String description;


    @Column(nullable = false)
    private Date entryDate;


    public Entry() {
        this.entryDate = new Date();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
