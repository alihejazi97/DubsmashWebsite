package com.ali.database.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "hash", name = "fuckingConstraint"))
public class Dubsmash {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long getId() {
        return id;
    }

    @NotNull
    public String hash;

    @Column
    @NotNull
    public int rating;

    @Column
    @NotNull
    public String fileAddress;

    @Column
    @NotNull
    public String coverFileAddress;

    @ManyToOne
    @JoinColumn
    @NotNull
    public User dubsmashCreator;

    @Column
    @NotNull
    public String dubsmashTitle;

    public Dubsmash(String hash, @NotNull String fileAddress, @NotNull String coverFileAddress, @NotNull User dubsmashCreator, @NotNull String dubsmashTitle) {
        this.hash = hash;
        this.fileAddress = fileAddress;
        this.coverFileAddress = coverFileAddress;
        this.dubsmashCreator = dubsmashCreator;
        this.dubsmashTitle = dubsmashTitle;
    }

    public Dubsmash() {
    }
}

