package com.tpreactapisec.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "authorities")
public class Authority {


        @Id
        @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
        @GenericGenerator(name = "native",strategy = "native")
        private int id;

        private String name;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

    }

