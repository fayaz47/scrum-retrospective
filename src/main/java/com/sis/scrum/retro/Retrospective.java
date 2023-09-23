package com.sis.scrum.retro;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="retrospective")
public class Retrospective {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private    UUID name;
    private   String summary;
    private   Date date;
    private   Set<String> participants;
    @OneToMany(targetEntity=Feedback.class, mappedBy="retrospective",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private   List<Feedback> feedback;

}
