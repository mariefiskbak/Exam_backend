package entities;

import javax.persistence.*;

@Entity
@Table(name = "boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boat_id", nullable = true)
    private Long id;
//    private String brand;
//    private String model;
//    private String name;
//    private String image;
//
//
//
//    @ManyToMany(mappedBy = "boats")
//    private Set<Owner> owners = new HashSet<>();
//    @ManyToOne //(cascade = { CascadeType.PERSIST, CascadeType.MERGE}) //owning side
//    private Harbour harbour;

    public Boat() {
    }

//    public Boat(BoatDTO boatDTO) {
//        if(boatDTO.getId() != null){
//            this.id = boatDTO.getId();
//        }
//        this.brand = boatDTO.getBrand();
//        this.model = boatDTO.getModel();
//        this.name = boatDTO.getName();
//        this.image = boatDTO.getImage();
//        this.harbour = new Harbour(boatDTO.getHarbour().getId(),
//                boatDTO.getHarbour().getName(),
//                boatDTO.getHarbour().getAddress(),
//                boatDTO.getHarbour().getCapacity());
////        Ifølge domænediagram må owners ikke være tom, men...
//        if(boatDTO.getOwners() != null) {
//            boatDTO.getOwners().forEach(ownerDTO -> {
//                this.addOwner(new Owner(ownerDTO.getName(), ownerDTO.getAddress(), ownerDTO.getPhone()));
//
//            });
//        }
//    }




}