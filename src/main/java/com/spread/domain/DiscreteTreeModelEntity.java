package com.spread.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discrete_tree_models")
@Getter @Setter @NoArgsConstructor @Data
public class DiscreteTreeModelEntity implements IModel {

    @Id
    @Column(name="sessionId", nullable = false)
    String sessionId;

    @Column(name = "tree_filename", nullable = false)
    private String treeFilename;

    @Column(name = "location_attribute", nullable = true)
    private String locationAttribute;

    @Column(name = "mrsd", nullable = true)
    private String mrsd;

    @Column(name = "timescale_multiplier", nullable = true)
    private Double timescaleMultiplier;

    @Column(name = "geojson_filename", nullable = true)
    private String geojsonFilename;

    @Column(name = "output_filename", nullable = true)
    private String outputFilename;

    @Column(name = "ipfs_hash", nullable = true)
    private String ipfsHash;

    @JsonManagedReference("tree-attributes")
    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DiscreteAttributeEntity> attributes;

    @JsonManagedReference("tree-locations")
    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LocationEntity> locations;

    @Enumerated(EnumType.STRING)
    private Status status;

    public DiscreteTreeModelEntity(String sessionId, String treeFilename) {
        this.treeFilename = treeFilename;
        this.sessionId = sessionId;
    }

}
