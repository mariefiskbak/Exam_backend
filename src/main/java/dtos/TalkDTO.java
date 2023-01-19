package dtos;

import entities.Speaker;
import entities.Talk;

import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link entities.Talk} entity
 */
public class TalkDTO implements Serializable {
    private final Long id;
    private final String topic;
    private final int duration;
    private final String propsList;
    private final Set<SpeakerInnerDTO> speakerSet = new HashSet<>();

    public TalkDTO(Long id, String topic, int duration, String propsList) {
        this.id = id;
        this.topic = topic;
        this.duration = duration;
        this.propsList = propsList;
    }

    public TalkDTO(Talk talk) {
        this.id = talk.getId();
        this.topic = talk.getTopic();
        this.duration = talk.getDuration();
        this.propsList = talk.getPropsList();
        talk.getSpeakerSet().forEach(speaker -> {
            speakerSet.add(new SpeakerInnerDTO(speaker));
        });


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

    public Set<SpeakerInnerDTO> getSpeakerSet() {
        return speakerSet;
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
                Objects.equals(this.speakerSet, entity.speakerSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, duration, propsList, speakerSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "topic = " + topic + ", " +
                "duration = " + duration + ", " +
                "propsList = " + propsList + ", " +
                "speakerSet = " + speakerSet + ")";
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
}