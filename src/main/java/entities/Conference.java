package entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "conference")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String location;
    private int capacity;
    private Date date;
    private Time time;

    @OneToMany(mappedBy = "conference")
    private Set<Talk> talks = new LinkedHashSet<>();


    public Conference() {
    }



    public Conference(Long id, String name, String location, int capacity, Date date, Time time, Set<Talk> talks) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.date = date;
        this.time = time;
        this.talks = talks;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public void addTalksToSet(Talk talk) {
        this.talks.add(talk);
        talk.setConference(this);
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference that = (Conference) o;
        return capacity == that.capacity && id.equals(that.id) && name.equals(that.name) && Objects.equals(location, that.location) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, capacity, date, time);
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", date=" + date +
                ", time=" + time +
                ", talks=" + talks +
                '}';
    }
}