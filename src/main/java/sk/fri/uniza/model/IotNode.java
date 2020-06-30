package sk.fri.uniza.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.SessionFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;

@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "IotNode_findAll",
                query = "from IotNode"),
        @org.hibernate.annotations.NamedQuery(name = "IotNode_findByHouseHold",
                query = "from IotNode where household_id = :householdID"),
})
@Entity
public class IotNode {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ApiModelProperty(example = "1")
    private Long id;

    @NotEmpty
    @ApiModelProperty(example = "Marek je gay")
    private String Name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "household_id", nullable = false)
    private HouseHold houseHold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public HouseHold getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(HouseHold houseHold) {
        this.houseHold = houseHold;
    }
}
