package com.sis.scrum.retro;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Retrospective {

    @Id
    private    UUID name;
    private   String summary;
    private Date date;
    private List<String> names;
    private Feedbacktype feedback;

}
