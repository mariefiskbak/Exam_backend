package dtos;

import entities.Conference;
import entities.Speaker;
import entities.Talk;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

/**
 * A DTO for the {@link entities.Talk} entity
 */
public class TalkDTO implements Serializable {
    private final Long id;
    private final String topic;
    private final int duration;
    private final String propsList;
    private final Set<SpeakerInnerDTO> speakers = new HashSet<>();
    private final ConferenceInnerDTO conference;


    public TalkDTO(Long id, String topic, int duration, String propsList, ConferenceInnerDTO conference) {
        this.id = id;
        this.topic = topic;
        this.duration = duration;
        this.propsList = propsList;
        this.conference = conference;
    }

//    public TalkDTO(Long id, String topic, int duration, String propsList) {
//        this.id = id;
//        this.topic = topic;
//        this.duration = duration;
//        this.propsList = propsList;
//    }
    public TalkDTO(Talk talk) {
        this.id = talk.getId();
        this.topic = talk.getTopic();
        this.duration = talk.getDuration();
        this.propsList = talk.getPropsList();
        talk.getSpeakers().forEach(speaker -> {
            speakers.add(new SpeakerInnerDTO(speaker));
        });
        this.conference = new ConferenceInnerDTO(talk.getConference());

    }

    public static List<TalkDTO> getDTOs(List<Talk> talks) {
        List<TalkDTO> talkDTOList = new ArrayList<>();
        talks.forEach(talk -> {
            talkDTOList.add(new TalkDTO(talk));
        });
        return talkDTOList;
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

    public Set<SpeakerInnerDTO> getSpeakers() {
        return speakers;
    }

    public TalkDTO.ConferenceInnerDTO getConference() {
        return conference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TalkDTO entity = (TalkDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.topic, entity.topic) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.propsList, entity.propsList) &&
                Objects.equals(this.speakers, entity.speakers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, duration, propsList, speakers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "topic = " + topic + ", " +
                "duration = " + duration + ", " +
                "propsList = " + propsList + ", " +
                "speakers = " + speakers + ", " +
                "conference = " + conference + ")";
    }

    /**
     * A DTO for the {@link entities.Speaker} entity
     */
    public static class SpeakerInnerDTO implements Serializable {
        private final Long id;
        private final String name;
        private final String profession;
        private final String gender;

        public SpeakerInnerDTO(Long id, String name, String profession, String gender) {
            this.id = id;
            this.name = name;
            this.profession = profession;
            this.gender = gender;
        }

        public SpeakerInnerDTO(Speaker speaker) {
            this.id = speaker.getId();
            this.name = speaker.getName();
            this.profession = speaker.getProfession();
            this.gender = speaker.getGender();
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getProfession() {
            return profession;
        }

        public String getGender() {
            return gender;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpeakerInnerDTO entity = (SpeakerInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.profession, entity.profession) &&
                    Objects.equals(this.gender, entity.gender);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, profession, gender);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "profession = " + profession + ", " +
                    "gender = " + gender + ")";
        }
    }

    /**
     * A DTO for the {@link Conference} entity
     */
    public static class ConferenceInnerDTO implements Serializable {
        private final Long id;
        private final String name;
        private final String location;
        private final int capacity;
        private final java.sql.Date date;
        private final Time time;

        public ConferenceInnerDTO(Long id, String name, String location, int capacity, java.sql.Date date, Time time) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.capacity = capacity;
            this.date = date;
            this.time = time;
        }

        public ConferenceInnerDTO(Conference conference) {
            this.id = conference.getId();
            this.name = conference.getName();
            this.location = conference.getLocation();
            this.capacity = conference.getCapacity();
            this.date = conference.getDate();
            this.time = conference.getTime();
        }

        public static List<ConferenceInnerDTO> getDTOs(List<Conference> conferences) {
            List<ConferenceInnerDTO> conferenceDTOList = new ArrayList<>();
            conferences.forEach(c -> {
                conferenceDTOList.add(new ConferenceInnerDTO(c));
            });
            return conferenceDTOList;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TalkDTO.ConferenceInnerDTO entity = (TalkDTO.ConferenceInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.location, entity.location) &&
                    Objects.equals(this.capacity, entity.capacity) &&
                    Objects.equals(this.date, entity.date) &&
                    Objects.equals(this.time, entity.time);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, location, capacity, date, time);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "location = " + location + ", " +
                    "capacity = " + capacity + ", " +
                    "date = " + date + ", " +
                    "time = " + time + ")";
        }
    }
}