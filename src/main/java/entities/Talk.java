package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "talk")
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String topic;
    private int duration;
    //TODO Props can be changed to its own entitiy
    private String propsList;

    @ManyToMany(mappedBy = "talkSet")
    private Set<Speaker> speakerSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;


    public Talk() {
    }


    public Talk(Long id, String topic, int duration, String propsList, Set<Speaker> speakerSet, Conference conference) {
        this.id = id;
        this.topic = topic;
        this.duration = duration;
        this.propsList = propsList;
        this.speakerSet = speakerSet;
        this.conference = conference;
    }

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public int getDuration() {
        return duration;
    }

    public String getPropsList() {
        return propsList;
    }

    public Set<Speaker> getSpeakerSet() {
        return speakerSet;
    }

    public Conference getConference() {
        return conference;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Talk talk = (Talk) o;
        return duration == talk.duration && id.equals(talk.id) && topic.equals(talk.topic) && Objects.equals(propsList, talk.propsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, duration, propsList);
    }

    @Override
    public String toString() {
        return "Talk{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", duration=" + duration +
                ", propsList='" + propsList + '\'' +
                ", speakerSet=" + speakerSet +
                ", conference=" + conference +
                '}';
    }
}