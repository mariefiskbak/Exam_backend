package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "speaker")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String profession;
    //TODO Gender can be changed to its own entity
    private String gender;

    @JoinTable(name = "speaker_talks", joinColumns = {
            @JoinColumn(name = "speaker_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "talk_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Talk> talkSet = new HashSet<>();



    public Speaker() {
    }


    public Speaker(String name, String profession, String gender) {
        this.name = name;
        this.profession = profession;
        this.gender = gender;
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

    public Set<Talk> getTalkSet() {
        return talkSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speaker speaker = (Speaker) o;
        return id.equals(speaker.id) && name.equals(speaker.name) && Objects.equals(profession, speaker.profession) && Objects.equals(gender, speaker.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, profession, gender);
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", gender='" + gender + '\'' +
                ", talkSet=" + talkSet +
                '}';
    }

    public void addTalk(Talk talk) {
        talkSet.add(talk);
    }
}


