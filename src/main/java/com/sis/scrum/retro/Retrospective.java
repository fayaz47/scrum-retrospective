package com.sis.scrum.retro;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Retrospective {

    private    UUID name;
    private   String summary;
    private Date data;
    private List<String> names;

}
